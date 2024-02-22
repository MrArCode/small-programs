#include <iostream>
#include <conio.h>

using namespace std;
float x, y;
char wybor;

int main()
{
    for (;;)
    {
        cout << "Pierwsza liczba: ";
        cin >> x;

        cout << "Druga liczba: ";
        cin >> y;

        cout << "MENU GŁÓWNE" << endl;
        cout << "====================" << endl;
        cout << "1. Dodawanie" << endl;
        cout << "2. Odejmowanie" << endl;
        cout << "3. Mnożenie" << endl;
        cout << "4. Dzielnie" << endl;
        cout << "5. Zakończ działanie programu" << endl;
        cout << "Podaj numer działania jakie trzeba wykonać: ";
        wybor = getch();

        switch (wybor)
        {
        case ('1'):
            cout << "Wynik to " << x + y << endl;
            break;
        case ('2'):
            cout << "Wynik to " << x - y << endl;
            break;
        case ('3'):
            cout << "Wynik to " << x * y << endl;
            break;
        case ('4'):
            cout << "Wynik to " << x / y << endl;
            break;

        case ('5'):
            exit(0);
            break;

        default:
            break;
        }
        getchar();
        getchar();
    }

    return 0;
}