#include <iostream>
#include <cstdlib>
#include <time.h>
#include <stdio.h>

using namespace std;

int liczbaWylosowana, liczbaUżytkownika, licznikPrób;

int main()
{
    cout << "Pomyślałem sobie liczbe od 1..10" << endl;
    srand(time(NULL));
    liczbaWylosowana = rand() % 10 + 1;

    while (liczbaUżytkownika != liczbaWylosowana)
    {
        cout << "Zgadnij jaka: ";
        cin >> liczbaUżytkownika;
        licznikPrób++;
        if (liczbaUżytkownika > liczbaWylosowana)
        {
            cout << "Podałeś za duża liczbe" << endl;
        }
        else if (liczbaUżytkownika < liczbaWylosowana)
        {
            cout << "Podałeś za małą liczbe" << endl;
        }
    }
    cout << "Gratulacje zgadłeś, szukana liczba to " << liczbaWylosowana << " ilość prób: " << licznikPrób;
    getchar();
    return 0;
}