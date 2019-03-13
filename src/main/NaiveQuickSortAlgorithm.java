package main;



class NaiveQuickSortAlgorithm extends SortAlgorithm
{
	Instrumentation ins = Instrumentation.getInstance();

	int[] sort(int a[], int lo0, int hi0)
	{
		
		int arr[] = new int[1]; //added
		arr[0] = 0;				//added
		
		int lo = lo0;
		int hi = hi0;
		super.lowMarker = lo0;
		super.hiMarker = hi0;


		if (lo >= hi)
		{
			return a;
		} else if (lo == hi - 1)
		{
			/*
			 *  sort a two element list by swapping if necessary 
			 */
			if (a[lo] > a[hi])
			{
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
				super.moves++;
				super.updateAllViews();
			}
			return a;
		}

		/*
		 *  Pick a pivot.  This one is pretty 'naive' (dumb) for the worst case.
		 */
		int pivot = a[hi];
		super.other++;

		while (lo < hi)
		{
			/*
			 *  Search forward from a[lo] until an element is found that
			 *  is greater than the pivot or lo >= hi 
			 */
			super.compares++;
			while (a[lo] <= pivot && lo < hi)
			{
				lo++;
				super.compares++;
			}

			/*
			 *  Search backward from a[hi] until element is found that
			 *  is less than the pivot, or lo >= hi
			 */
			super.compares++;
			while (pivot <= a[hi] && lo < hi)
			{
				hi--;
				super.compares++;
			}

			/*
			 *  Swap elements a[lo] and a[hi]
			 */
			if (lo < hi)
			{
				int T = a[lo];
				a[lo] = a[hi];
				a[hi] = T;
				super.moves++;
			}
			super.updateAllViews();

			if (stopRequested)
			{
				return arr;
			}
		}

		/*
		 *  Put the median in the "center" of the list
		 */
		a[hi0] = a[hi];
		a[hi] = pivot;
		super.moves += 2;
		super.updateAllViews();

		/*
		 *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
		 *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
		 *  pivot.
		 */

		a = sort(a, lo0, lo - 1);
		a = sort(a, hi + 1, hi0);
		
		return a;
		
	}

	int[] sort(int a[])
	{
		int arr[] = new int[a.length];
		arr = sort(a, 0, a.length - 1);
		super.updateAllViews(-1, -1);
		return arr;
	}
}