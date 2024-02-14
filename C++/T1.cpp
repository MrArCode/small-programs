#include <iostream>

using namespace std;

int liczbaUczniow, liczbaCukierkow, x, y;

int main()
{
    cout << "Ilu uczniów jest w twojej klasie:";
    cin >> liczbaUczniow;

    cout << "Ile cukierkow kupila mama: ";
    cin >> liczbaCukierkow;

    x = liczbaCukierkow / (liczbaUczniow - 1);
    cout << "Cukierkow dla kazdego ucznia jest: " << x << endl;

    y = liczbaCukierkow - x * (liczbaUczniow - 1);
    cout << "Dla Jasia zostaje na wieczor: " << y;

    return 0;
}