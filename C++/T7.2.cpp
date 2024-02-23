#include <iostream>
#include <fstream>

using namespace std;

string imie, nazwisko;
int numerTelefonu;

int main()
{
    fstream plik;
    plik.open("/Users/arturcegielka-posluszny/Desktop/Projekty/small-programs/C++/test.txt", ios::in);

    if (plik.good())
    {
    }

    plik << imie << endl;
    plik << nazwisko << endl;
    plik << numerTelefonu << endl;
    plik.close();

    return 0;
}
