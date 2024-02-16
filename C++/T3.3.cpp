#include <iostream>

using namespace std;

int liczbaBakterii = 1, liczbaGodzin = 0;

int main()
{

    while (liczbaBakterii < 1000000000)
    {
        liczbaGodzin++;
        liczbaBakterii *= 2;

        cout << "Ilość godzin, które upłyneły: " << liczbaGodzin;
        cout << ", Ilość bakterii: " << liczbaBakterii << endl;
    }

    return 0;
}
