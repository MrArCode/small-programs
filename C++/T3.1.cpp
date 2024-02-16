#include <iostream>
#include <unistd.h>
#include <cstdlib>

using namespace std;

int main()
{
    for (int i = 15; i >= 0; i--)
    {
        sleep(1);
        system("cls");
        cout << i << endl;
    }
    cout << "Wybuch!!!!!";
    return 0;
}
