import java.util.Scanner;

class Video {
    int id;
    String title;
    boolean isRented;

    Video(int id, String title) {
        this.id = id;
        this.title = title;
        this.isRented = false;
    }

    void display() {
        System.out.print("ID: " + id + ", Title: " + title + ", Status: ");
        if (isRented) {
            System.out.println("Rented");
        } else {
            System.out.println("Available");
        }
    }
}

public class VideoInventorySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Video[] videos = new Video[100];
        int count = 0;
        int choice;

        System.out.println("\nWelcome to Video Inventory System");

        do {
            System.out.println("\n1. Add Video");
            System.out.println("2. View All Videos");
            System.out.println("3. Rent Video");
            System.out.println("4. Return Video");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Video ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Video Title: ");
                String title = sc.nextLine();

                videos[count] = new Video(id, title);
                count++;
                System.out.println("Video added successfully.");
            } else if (choice == 2) {
                if (count == 0) {
                    System.out.println("No videos available.");
                } else {
                    for (int i = 0; i < count; i++) {
                        videos[i].display();
                    }
                }
            } else if (choice == 3) {
                System.out.print("Enter Video ID to rent: ");
                int id = sc.nextInt();
                boolean found = false;
                for (int i = 0; i < count; i++) {
                    if (videos[i].id == id) {
                        if (!videos[i].isRented) {
                            videos[i].isRented = true;
                            System.out.println("Video rented successfully.");
                        } else {
                            System.out.println("Video is already rented.");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Video not found.");
                }
            } else if (choice == 4) {
                System.out.print("Enter Video ID to return: ");
                int id = sc.nextInt();
                boolean found = false;
                for (int i = 0; i < count; i++) {
                    if (videos[i].id == id) {
                        if (videos[i].isRented) {
                            videos[i].isRented = false;
                            System.out.println("Video returned successfully.");
                        } else {
                            System.out.println("Video was not rented.");
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Video not found.");
                }
            } else if (choice == 5) {
                System.out.println("Exiting Video Inventory System...");
            } else {
                System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }
}
