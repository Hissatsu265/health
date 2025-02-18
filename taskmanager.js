import React, { useState, useEffect } from 'react';

const TaskManager = () => {
  const [tasks, setTasks] = useState(() => {
    const savedTasks = localStorage.getItem('tasks');
    return savedTasks ? JSON.parse(savedTasks) : [];
  });
  const [newTask, setNewTask] = useState('');
  const [filter, setFilter] = useState('all'); // all, active, completed
  const [editingId, setEditingId] = useState(null);
  const [editingText, setEditingText] = useState('');
  const [searchQuery, setSearchQuery] = useState('');
  const [sortBy, setSortBy] = useState('date'); // date, priority
  const [darkMode, setDarkMode] = useState(false);

  // Save tasks to localStorage whenever they change
  useEffect(() => {
    localStorage.setItem('tasks', JSON.stringify(tasks));
  }, [tasks]);

  // Add new task
  const handleAddTask = (e) => {
    e.preventDefault();
    if (newTask.trim()) {
      const task = {
        id: Date.now(),
        text: newTask.trim(),
        completed: false,
        createdAt: new Date().toISOString(),
        priority: 'medium',
        dueDate: null,
        tags: []
      };
      setTasks([...tasks, task]);
      setNewTask('');
    }
  };

  // Toggle task completion
  const toggleComplete = (id) => {
    setTasks(tasks.map(task =>
      task.id === id ? { ...task, completed: !task.completed } : task
    ));
  };

  // Delete task
  const deleteTask = (id) => {
    if (window.confirm('Are you sure you want to delete this task?')) {
      setTasks(tasks.filter(task => task.id !== id));
    }
  };

  // Start editing task
  const startEditing = (task) => {
    setEditingId(task.id);
    setEditingText(task.text);
  };

  // Save edited task
  const saveEdit = (id) => {
    setTasks(tasks.map(task =>
      task.id === id ? { ...task, text: editingText } : task
    ));
    setEditingId(null);
    setEditingText('');
  };

  // Update task priority
  const updatePriority = (id, priority) => {
    setTasks(tasks.map(task =>
      task.id === id ? { ...task, priority } : task
    ));
  };

  // Add tag to task
  const addTag = (taskId, tag) => {
    setTasks(tasks.map(task =>
      task.id === taskId && !task.tags.includes(tag)
        ? { ...task, tags: [...task.tags, tag] }
        : task
    ));
  };

  // Remove tag from task
  const removeTag = (taskId, tagToRemove) => {
    setTasks(tasks.map(task =>
      task.id === taskId
        ? { ...task, tags: task.tags.filter(tag => tag !== tagToRemove) }
        : task
    ));
  };

  // Filter tasks based on current filter and search query
  const filteredTasks = tasks
    .filter(task => {
      if (filter === 'active') return !task.completed;
      if (filter === 'completed') return task.completed;
      return true;
    })
    .filter(task =>
      task.text.toLowerCase().includes(searchQuery.toLowerCase())
    );

  // Sort tasks based on current sort criteria
  const sortedTasks = [...filteredTasks].sort((a, b) => {
    if (sortBy === 'date') {
      return new Date(b.createdAt) - new Date(a.createdAt);
    }
    if (sortBy === 'priority') {
      const priorityOrder = { high: 3, medium: 2, low: 1 };
      return priorityOrder[b.priority] - priorityOrder[a.priority];
    }
    return 0;
  });

  // Calculate task statistics
  const stats = {
    total: tasks.length,
    completed: tasks.filter(task => task.completed).length,
    active: tasks.filter(task => !task.completed).length
  };

  return (
    <div className={`task-manager ${darkMode ? 'dark-mode' : ''}`}>
      <header>
        <h1>Task Manager</h1>
        <button onClick={() => setDarkMode(!darkMode)}>
          {darkMode ? '☀️' : '🌙'}
        </button>
      </header>

      <div className="stats">
        <span>Total: {stats.total}</span>
        <span>Completed: {stats.completed}</span>
        <span>Active: {stats.active}</span>
      </div>

      <form onSubmit={handleAddTask}>
        <input
          type="text"
          value={newTask}
          onChange={(e) => setNewTask(e.target.value)}
          placeholder="Add new task..."
        />
        <button type="submit">Add Task</button>
      </form>

      <div className="filters">
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder="Search tasks..."
        />
        <select value={filter} onChange={(e) => setFilter(e.target.value)}>
          <option value="all">All</option>
          <option value="active">Active</option>
          <option value="completed">Completed</option>
        </select>
        <select value={sortBy} onChange={(e) => setSortBy(e.target.value)}>
          <option value="date">Sort by Date</option>
          <option value="priority">Sort by Priority</option>
        </select>
      </div>

      <ul className="task-list">
        {sortedTasks.map(task => (
          <li key={task.id} className={task.completed ? 'completed' : ''}>
            {editingId === task.id ? (
              <div className="edit-mode">
                <input
                  type="text"
                  value={editingText}
                  onChange={(e) => setEditingText(e.target.value)}
                />
                <button onClick={() => saveEdit(task.id)}>Save</button>
              </div>
            ) : (
              <div className="task-content">
                <input
                  type="checkbox"
                  checked={task.completed}
                  onChange={() => toggleComplete(task.id)}
                />
                <span className="task-text">{task.text}</span>
                <div className="task-actions">
                  <select
                    value={task.priority}
                    onChange={(e) => updatePriority(task.id, e.target.value)}
                  >
                    <option value="low">Low</option>
                    <option value="medium">Medium</option>
                    <option value="high">High</option>
                  </select>
                  <button onClick={() => startEditing(task)}>Edit</button>
                  <button onClick={() => deleteTask(task.id)}>Delete</button>
                </div>
              </div>
            )}
            <div className="tags">
              {task.tags.map(tag => (
                <span key={tag} className="tag">
                  {tag}
                  <button onClick={() => removeTag(task.id, tag)}>×</button>
                </span>
              ))}
              <button
                onClick={() => {
                  const tag = prompt('Enter new tag:');
                  if (tag) addTag(task.id, tag);
                }}
              >
                +
              </button>
            </div>
          </li>
        ))}
      </ul>

      <style jsx>{`
        .task-manager {
          max-width: 800px;
          margin: 0 auto;
          padding: 20px;
        }

        .dark-mode {
          background-color: #1a1a1a;
          color: #ffffff;
        }

        header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 20px;
        }

        .stats {
          display: flex;
          gap: 20px;
          margin-bottom: 20px;
        }

        form {
          display: flex;
          gap: 10px;
          margin-bottom: 20px;
        }

        .filters {
          display: flex;
          gap: 10px;
          margin-bottom: 20px;
        }

        .task-list {
          list-style: none;
          padding: 0;
        }

        .task-list li {
          border: 1px solid #ddd;
          margin-bottom: 10px;
          padding: 10px;
          border-radius: 4px;
        }

        .task-content {
          display: flex;
          align-items: center;
          gap: 10px;
        }

        .task-actions {
          margin-left: auto;
          display: flex;
          gap: 10px;
        }

        .completed .task-text {
          text-decoration: line-through;
          color: #888;
        }

        .tags {
          display: flex;
          gap: 5px;
          margin-top: 5px;
        }

        .tag {
          background-color: #e0e0e0;
          padding: 2px 6px;
          border-radius: 3px;
          display: flex;
          align-items: center;
          gap: 4px;
        }

        button {
          padding: 5px 10px;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }

        input[type="text"] {
          padding: 5px;
          border: 1px solid #ddd;
          border-radius: 4px;
        }

        select {
          padding: 5px;
          border: 1px solid #ddd;
          border-radius: 4px;
        }
                select {
          padding: 5px;
          border: 1px solid #ddd;
          border-radius: 4px;
        }
                select {
          padding: 5px;
          border: 1px solid #ddd;
          border-radius: 4px;
        }
      `}</style>
    </div>
  );
};

export default TaskManager;
