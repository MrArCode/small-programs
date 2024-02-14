#include <iostream>

using namespace std;
int wiek;

int main()
{
    cout << "Ile masz lat";
    cin >> wiek;

    if (wiek < 18)
    {
        cout << "Nie możesz głosować, jesteś za młody";
    }
    else if (wiek >= 18 && wiek < 35)
    {
        cout << "Jest dobrze, twój wiek się zgadza, ale nie możesz kandydować na prezydenta";
    }
    else
    {
        cout << "Jest świetnie, możesz głosować i kandydować na prezdyenta";
    }

    return 0;
}
