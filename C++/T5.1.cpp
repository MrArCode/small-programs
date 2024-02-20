#include <iostream>

using namespace std;

double oceny[5], suma, średnia;

int main()
{
    for (int i = 0; i < 5; i++)
    {
        cout << "Podaj mi " << i + 1 << " ocene: ";
        cin >> oceny[i];
        suma += oceny[i];
    }
    cout << "Twoja średnia wynosi " << suma / 5;

    return 0;
}