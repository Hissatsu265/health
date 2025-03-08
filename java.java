
@SpringBootApplication
public class JavaWebApp {
    public static void main(String[] args) {
        SpringApplication.run(JavaWebApp.class, args);
    }
}

@RestController
@RequestMapping("/api")
class HelloController {
    @GetMapping("/")
    public String home() {
        return "Welcome to Java Web API!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello, " + name + "!";
    }
}

@RestController
@RequestMapping("/api/users")
class UserController {
    private final Map<Integer, String> users = new HashMap<>();

    @PostMapping("/add")
    public Map<String, String> addUser(@RequestParam int id, @RequestParam String name) {
        users.put(id, name);
        return Map.of("message", "User added successfully");
    }

    @GetMapping("/{id}")
    public Map<String, String> getUser(@PathVariable int id) {
        return users.containsKey(id) ? Map.of("id", String.valueOf(id), "name", users.get(id))
                                      : Map.of("error", "User not found");
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable int id) {
        if (users.remove(id) != null) {
            return Map.of("message", "User deleted successfully");
        } else {
            return Map.of("error", "User not found");
        }
    }
}
