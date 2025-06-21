#include <iostream>
using namespace std;

class Passenger {
public:
    int pnr;
    char name[50];
    int age;
    char gender;
};

class RailwayReservationSystem {
    Passenger booked[5];
    int bookedCount;

    Passenger waiting[10];
    int front, rear;

    int nextPNR;

public:
    RailwayReservationSystem() {
        bookedCount = 0;
        front = -1;
        rear = -1;
        nextPNR = 1000;
    }

    void copy(char dest[], char src[]) {
        int i = 0;
        while (src[i] != '\0') {
            dest[i] = src[i];
            i++;
        }
        dest[i] = '\0';
    }

  void readInput(char str[]) {
    char ch;
    int i = 0;
    while ((ch = cin.get()) != '\n' && i < 49) {
        if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || ch == ' ') {
            str[i++] = ch;
       }
    }
    str[i] = '\0';
}


    void enqueue(Passenger p) {
        if ((rear + 1) % 10 == front) {
            cout << "Waiting list full\n";
            return;
        }
        if (front == -1) front = 0;
        rear = (rear + 1) % 10;
        waiting[rear] = p;
        cout << "Added to waiting list. PNR: " << p.pnr << endl;
    }

    Passenger dequeue() {
        Passenger temp;
        temp.pnr = -1;
        temp.name[0] = '\0';
        temp.age = 0;
        temp.gender = ' ';
        if (front == -1) return temp;

        temp = waiting[front];
        if (front == rear) front = rear = -1;
        else front = (front + 1) % 10;
        return temp;
    }

    void bookTicket() {
        Passenger p;
        p.pnr = nextPNR++;
        cout << "Enter Name: ";
        cin.ignore(); // << THIS should be only here
        readInput(p.name);
        cout << "Enter Age: ";
        cin >> p.age;
        cout << "Enter Gender (M/F): ";
        cin >> p.gender;

        if (bookedCount < 5) {
            booked[bookedCount++] = p;
            cout << "Ticket booked. PNR: " << p.pnr << endl;
        } else {
            enqueue(p);
        }
    }

    void cancelTicket() {
        int pnr;
        cout << "Enter PNR to cancel: ";
        cin >> pnr;

        bool found = false;
        for (int i = 0; i < bookedCount; i++) {
            if (booked[i].pnr == pnr) {
                found = true;
                for (int j = i; j < bookedCount - 1; j++) {
                    booked[j] = booked[j + 1];
                }
                bookedCount--;
                cout << "Ticket cancelled.\n";

                Passenger moved = dequeue();
                if (moved.pnr != -1) {
                    booked[bookedCount++] = moved;
                    cout << "Moved from waiting list to booked. PNR: " << moved.pnr << endl;
                }

                break;
            }
        }
        if (!found) {
            cout << "PNR not found in booked tickets.\n";
        }
    }

    void viewBooked() {
        if (bookedCount == 0) {
            cout << "No confirmed tickets.\n";
            return;
        }
        cout << "\nConfirmed Tickets:\n";
        for (int i = 0; i < bookedCount; i++) {
            cout << "PNR: " << booked[i].pnr << ", Name: " << booked[i].name << ", Age: " << booked[i].age << ", Gender: " << booked[i].gender << endl;
        }
    }

    void viewWaiting() {
        if (front == -1) {
            cout << "No passengers in waiting list.\n";
            return;
        }
        cout << "\nWaiting List:\n";
        int i = front;
        while (true) {
            cout << "PNR: " << waiting[i].pnr << ", Name: " << waiting[i].name << ", Age: " << waiting[i].age << ", Gender: " << waiting[i].gender << endl;
            if (i == rear) break;
            i = (i + 1) % 10;
        }
    }
};

int main() {
    RailwayReservationSystem s;
    int choice;

    cout << "\nWelcome to Railway Reservation System\n";

    do {
        cout << "\n1. Book Ticket\n2. Cancel Ticket\n3. View Booked\n4. View Waiting\n5. Exit\nEnter your choice: ";
        cin >> choice;

        if (choice == 1) s.bookTicket();
        else if (choice == 2) s.cancelTicket();
        else if (choice == 3) s.viewBooked();
        else if (choice == 4) s.viewWaiting();
        else if (choice == 5) cout << "Exiting...\n";
        else cout << "Invalid choice\n";

    } while (choice != 5);

    return 0;