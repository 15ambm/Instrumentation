package main;
class ExtraStorageMergeSortAlgorithm extends SortAlgorithm
{
	int[] sort(int a[], int lo, int hi, int scratch[])
	{
		super.updateAllViews(lo, hi);
		
		int t[] = new int[1];
		t[0] = 0; 
		
		if (super.stopRequested)
		{
			return t; 
		}
		
		if (lo >= hi)
		{
			return a; /* a[lo] is sorted already   */
		}

		int mid = (lo + hi) / 2;
		a = sort(a, lo, mid, scratch); /* Sort sublist a[lo..mid]   */
		a = sort(a, mid + 1, hi, scratch); /* Sort sublist a[mid+1..hi] */

		if (super.stopRequested)
		{
			return a;
		}

		// Merge results
		int k, t_lo = lo, t_hi = mid + 1;
		super.lowMarker = lo;
		super.hiMarker = hi;
		for (k = lo; k <= hi; k++)
		{
			/* Merge sorted sublists    */
			super.compares++;
			if ((t_lo <= mid) && ((t_hi > hi) || (a[t_lo] < a[t_hi])))
			{
				scratch[k] = a[t_lo++];
			} else
			{
				scratch[k] = a[t_hi++];
			}
			super.moves++;
			super.activeMarker = k;
			super.updateAllViews();
		}

		for (k = lo; k <= hi; k++)
		{
			a[k] = scratch[k]; /* Copy back to a   */
			super.moves++;
			super.activeMarker = k;
			super.updateAllViews();
		}
		return a;
	}

	int[] sort(int a[])
	{
		int scratch[] = new int[a.length];
		a = sort(a, 0, a.length - 1, scratch);
		super.updateAllViews(-1, -1);
		return a;
	}
}