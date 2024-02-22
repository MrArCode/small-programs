#include <iostream>

using namespace std;

int numerMiesiąca;

int main()
{

    cout << "Podaj numer miesiąca ";
    if (!(cin >> numerMiesiąca))
    {
        cerr << "To nie jest liczba";
        exit(0);
    }
    switch (numerMiesiąca)
    {
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:

        cout << "Miesiąc ma 31 dni";
        break;
    case 4:
    case 6:
    case 9:
    case 11:
        cout << "Miesiąc ma 30 dni";
        break;
    case 2:
        int rok;
        cout << "Podaj rok: ";
        cin >> rok;
        if (((rok % 4 == 0) && (rok % 100 != 0)) || (rok % 400 == 0))
        {
            cout << "Miesiąc ma 29";
        }
        else
        {
            cout << "Miesiąc ma 28 dni";
        }
        break;

    default:
        cout << "Niepoprawny numer";
        break;
    }

    return 0;
}