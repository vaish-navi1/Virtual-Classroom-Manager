import java.util.*;

class Classroom {
    private String name;
    private List<Student> students;
    private List<String> assignments;

    public Classroom(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getId() + " has been enrolled in " + name + ".");
    }

    public void scheduleAssignment(String assignment) {
        assignments.add(assignment);
        System.out.println("Assignment for " + name + " has been scheduled.");
    }

    public void submitAssignment(String studentId, String assignment) {
        System.out.println("Assignment submitted by Student " + studentId + " in " + name + ".");
    }

    public List<Student> getStudents() {
        return students;
    }
}

class Student {
    private String id;
    private List<String> submittedAssignments;

    public Student(String id) {
        this.id = id;
        this.submittedAssignments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void submitAssignment(String assignment) {
        submittedAssignments.add(assignment);
    }
}

public class VirtualClassroomManager {
    private Map<String, Classroom> classrooms;

    public VirtualClassroomManager() {
        this.classrooms = new HashMap<>();
    }

    public void addClassroom(String className) {
        if (!classrooms.containsKey(className)) {
            classrooms.put(className, new Classroom(className));
            System.out.println("Classroom " + className + " has been created.");
        } else {
            System.out.println("Classroom " + className + " already exists.");
        }
    }

    public void addStudent(String studentId, String className) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            Student student = new Student(studentId);
            classroom.addStudent(student);
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void scheduleAssignment(String className, String assignment) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.scheduleAssignment(assignment);
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public void submitAssignment(String studentId, String className, String assignment) {
        Classroom classroom = classrooms.get(className);
        if (classroom != null) {
            classroom.submitAssignment(studentId, assignment);
        } else {
            System.out.println("Classroom " + className + " does not exist.");
        }
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter command:");
            String command = scanner.nextLine().trim();
            String[] parts = command.split(" ", 4);  // Increase split limit to capture all parts, especially for assignment details

            if (parts.length < 2) {
                System.out.println("Invalid command format.");
                continue;
            }

            String action = parts[0];
            try {
                switch (action) {
                    case "add_classroom":
                        if (parts.length == 2) {
                            manager.addClassroom(parts[1]);
                        } else {
                            System.out.println("Usage: add_classroom <class_name>");
                        }
                        break;
                    case "add_student":
                        if (parts.length == 3) {
                            manager.addStudent(parts[1], parts[2]);
                        } else {
                            System.out.println("Usage: add_student <student_id> <class_name>");
                        }
                        break;
                    case "schedule_assignment":
                        if (parts.length == 3) {
                            manager.scheduleAssignment(parts[1], parts[2]);
                        } else {
                            System.out.println("Usage: schedule_assignment <class_name> <assignment_details>");
                        }
                        break;
                    case "submit_assignment":
                        if (parts.length == 4) {
                            manager.submitAssignment(parts[1], parts[2], parts[3]);
                        } else {
                            System.out.println("Usage: submit_assignment <student_id> <class_name> <assignment_details>");
                        }
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + e.getMessage());
            }
        }
    }
}
