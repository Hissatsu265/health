package service

import (
    "context"
    "errors"
    "sync"
    "time"
)

// User represents the user entity
type User struct {
    ID        string    `json:"id"`
    Username  string    `json:"username"`
    Email     string    `json:"email"`
    Password  string    `json:"-"` // Password is not included in JSON
    CreatedAt time.Time `json:"created_at"`
    UpdatedAt time.Time `json:"updated_at"`
    IsActive  bool      `json:"is_active"`
    Role      string    `json:"role"`
}

// UserService handles user-related operations
type UserService struct {
    users  map[string]*User
    mutex  sync.RWMutex
    logger Logger
}

// Logger interface for dependency injection
type Logger interface {
    Info(msg string, fields ...interface{})
    Error(msg string, err error, fields ...interface{})
}

// NewUserService creates a new instance of UserService
func NewUserService(logger Logger) *UserService {
    return &UserService{
        users:  make(map[string]*User),
        logger: logger,
    }
}

// CreateUser creates a new user
func (s *UserService) CreateUser(ctx context.Context, user *User) error {
    if user == nil {
        return errors.New("user cannot be nil")
    }

    s.mutex.Lock()
    defer s.mutex.Unlock()

    // Check if username already exists
    for _, existingUser := range s.users {
        if existingUser.Username == user.Username {
            s.logger.Error("username already exists", errors.New("duplicate username"), 
                "username", user.Username)
            return errors.New("username already exists")
        }
    }

    now := time.Now()
    user.CreatedAt = now
    user.UpdatedAt = now
    user.IsActive = true

    s.users[user.ID] = user
    s.logger.Info("user created successfully", "user_id", user.ID)

    return nil
}

// GetUser retrieves a user by ID
func (s *UserService) GetUser(ctx context.Context, id string) (*User, error) {
    s.mutex.RLock()
    defer s.mutex.RUnlock()

    user, exists := s.users[id]
    if !exists {
        s.logger.Error("user not found", errors.New("user not found"), "user_id", id)
        return nil, errors.New("user not found")
    }

    return user, nil
}

// UpdateUser updates an existing user
func (s *UserService) UpdateUser(ctx context.Context, id string, updates map[string]interface{}) error {
    s.mutex.Lock()
    defer s.mutex.Unlock()

    user, exists := s.users[id]
    if !exists {
        s.logger.Error("user not found for update", errors.New("user not found"), "user_id", id)
        return errors.New("user not found")
    }

    // Apply updates
    for key, value := range updates {
        switch key {
        case "username":
            if username, ok := value.(string); ok {
                user.Username = username
            }
        case "email":
            if email, ok := value.(string); ok {
                user.Email = email
            }
        case "role":
            if role, ok := value.(string); ok {
                user.Role = role
            }
        case "is_active":
            if isActive, ok := value.(bool); ok {
                user.IsActive = isActive
            }
        }
    }

    user.UpdatedAt = time.Now()
    s.logger.Info("user updated successfully", "user_id", id)

    return nil
}

// DeleteUser deletes a user by ID
func (s *UserService) DeleteUser(ctx context.Context, id string) error {
    s.mutex.Lock()
    defer s.mutex.Unlock()

    if _, exists := s.users[id]; !exists {
        s.logger.Error("user not found for deletion", errors.New("user not found"), "user_id", id)
        return errors.New("user not found")
    }

    delete(s.users, id)
    s.logger.Info("user deleted successfully", "user_id", id)

    return nil
}

// ListUsers returns all active users
func (s *UserService) ListUsers(ctx context.Context) ([]*User, error) {
    s.mutex.RLock()
    defer s.mutex.RUnlock()

    users := make([]*User, 0, len(s.users))
    for _, user := range s.users {
        if user.IsActive {
            users = append(users, user)
        }
    }

    return users, nil
}

// SearchUsers searches for users based on criteria
func (s *UserService) SearchUsers(ctx context.Context, criteria map[string]interface{}) ([]*User, error) {
    s.mutex.RLock()
    defer s.mutex.RUnlock()

    results := make([]*User, 0)

    for _, user := range s.users {
        matches := true
        for key, value := range criteria {
            switch key {
            case "username":
                if username, ok := value.(string); ok && user.Username != username {
                    matches = false
                }
            case "role":
                if role, ok := value.(string); ok && user.Role != role {
                    matches = false
                }
            case "is_active":
                if isActive, ok := value.(bool); ok && user.IsActive != isActive {
                    matches = false
                }
            }
        }
        if matches {
            results = append(results, user)
        }
    }

    return results, nil
}
