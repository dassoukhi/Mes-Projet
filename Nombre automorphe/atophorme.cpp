#include <iostream>
#include <cmath>
using namespace std;

const long long int taille = 1000000000;

int main(int argc, char const *argv[])
{
	long long int div =10;

	for (long long int i = 1; i <= taille; ++i)
	{
		/* code */
		

 		long int k = pow(i,2);

 		if (k >= div*10)
		{
			/* code */
			div *=10;
			//cout<<" div "<<div<<" k "<<k<<endl;
			
		}
			/* code */
		for (long long int j = div; j >=10 ; j=j/10)
		{
			/* code */
			if (k % j == i )
			{
			cout<<"i : "<<i<<" la puissace est : "<<k<<endl;
			}
		}
		
		
	}

	return 0;
}						