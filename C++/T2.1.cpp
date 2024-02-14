#include <iostream>

using namespace std;
string PIN;

int main()
{
    cout << "Witaj w Banku, Podaj PIN: ";
    cin >> PIN;
    if (PIN != "1234")
    {
        cout << "Błąd, PIN nie poprawny";
    }
    else
    {
        cout << "Logowanie udane, wszedłeś do systemu";
    }

    return 0;
}
