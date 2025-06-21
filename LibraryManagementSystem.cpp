#include <iostream>
using namespace std;

class Book {
public:
    int id;
    char title[100];
    char author[100];
    bool issued;
};

class Library {
public:
    Book books[100];
    int count = 0;

    void removeNewline(char str[]) {
        int i = 0;
        while (str[i] != '\0') {
            if (str[i] == '\n') {
                str[i] = '\0';
                break;
            }
            i++;
        }
    }

    void copy(char dest[], const char src[]) {
        int i = 0;
        while (src[i] != '\0') {
            dest[i] = src[i];
            i++;
        }
        dest[i] = '\0';
    }

    void addBook(int id, char title[], char author[]) {
        books[count].id = id;
        copy(books[count].title, title);
        copy(books[count].author, author);
        books[count].issued = false;
        count++;
    }

    void showBooks() {
        for (int i = 0; i < count; i++) {
            cout << "ID: " << books[i].id << ", Title: " << books[i].title << ", Author: " << books[i].author;
            cout << ", Status: ";
            if (books[i].issued == true) {
                cout << "Issued";
            } else {
                cout << "Available";
            }
            cout << endl;
        }
    }

    void issueBook(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].id == id) {
                if (books[i].issued == false) {
                    books[i].issued = true;
                    cout << "Book issued successfully\n";
                } else {
                    cout << "Book is already issued\n";
                }
                return;
            }
        }
        cout << "Book not available\n";
    }

    void returnBook(int id) {
        for (int i = 0; i < count; i++) {
            if (books[i].id == id && books[i].issued == true) {
                books[i].issued = false;
                cout << "Book returned successfully\n";
                return;
            }
        }
        cout << "Book not found or not issued\n";
    }
};

int main() {
    Library lib;
    int choice = 0, id;
    char title[100], author[100];

    while (choice != 5) {
        cout << "\nWelcome to Library Management System\n";
        cout << "\n1. Add Book\n2. Show Books\n3. Issue Book\n4. Return Book\n5. Exit\nEnter choice: ";
        cin >> choice;

        if (choice == 1) {
            cout << "Enter ID: ";
            cin >> id;
            getchar();
            cout << "Enter Title: ";
            fgets(title, 100, stdin);
            lib.removeNewline(title);
            cout << "Enter Author: ";
            fgets(author, 100, stdin);
            lib.removeNewline(author);
            lib.addBook(id, title, author);
        } else if (choice == 2) {
            lib.showBooks();
        } else if (choice == 3) {
            cout << "Enter Book ID to issue: ";
            cin >> id;
            lib.issueBook(id);
        } else if (choice == 4) {
            cout << "Enter Book ID to return: ";
            cin >> id;
            lib.returnBook(id);
        } else if (choice == 5) {
            cout << "Exiting...\n";
        } else {
            cout << "Invalid choice\n";
        }
    }

    return 0;
}