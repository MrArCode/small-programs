#include <iostream>
#include <fstream>

using namespace std;

string imie, nazwisko;
int numerTelefonu;

int main()
{
    cout << "Podaj swoje imie: ";
    cin >> imie;

    cout << "Podaj swoje nazwisko: ";
    cin >> nazwisko;

    cout << "Podaj swoj numer telefonu: ";
    cin >> numerTelefonu;

    fstream plik;
    plik.open("/Users/arturcegielka-posluszny/Desktop/Projekty/small-programs/C++/test.txt", ios::out);
    plik << imie << endl;
    plik << nazwisko << endl;
    plik << numerTelefonu << endl;
    plik.close();

    return 0;
}
