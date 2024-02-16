#include <iostream>

using namespace std;

int wiek;
string imie;

int main()
{

    cout << "Podaj swój wiek: ";
    cin >> wiek;

    cout << "Podaj swoje imie: ";
    cin >> imie;

    for (int i = 1; i <= wiek; i++)
    {
        cout << i << ". " << imie << endl;
    }
    return 0;
}