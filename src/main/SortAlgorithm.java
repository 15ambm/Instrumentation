package main;

import java.io.IOException;
import java.util.ArrayList;

abstract class SortAlgorithm 
{
	/** The list of views to update when the model changes. */
	private ArrayList<IView> views = new ArrayList<IView>();
	
	/*
	 * Markers to mark the high and low bounds of the sort's progress
	 * or the high and low bounds of its current area of concern.
	 */
	protected int lowMarker = -1;
	protected int hiMarker = -1;
	/** 
	 * Marker for the element actively being considered or moved.
	 */
	protected int activeMarker = -1;

	/**
	 * Count the number of comparisons, movements, and other array accesses
	 * since the last time we reported to the views.
	 */
	protected int compares = 0;
	protected int moves = 0;
	protected int other = 0;

	/**
	 * Count the total number of comparisons, movements, and other array accesses.
	 */
	private int totCompares = 0;
	private int totMoves = 0;
	private int totOther = 0;

	/**
	 * When true, stop sorting.
	 */
	protected boolean stopRequested = false;
	
	/** 
	 * Add a new view to this model.
	 */
	public void addView(IView aView)
	{
		this.views.add(aView);
	}
	
	/** 
	 * Update all the views observing this model.
	 */
	protected void updateAllViews(int lowMarker, int hiMarker)
	{
		this.lowMarker = lowMarker;
		this.hiMarker = hiMarker;
		if (lowMarker == -1 && hiMarker == -1)
			this.activeMarker = -1;
		this.updateAllViews();
	}
	
	
	/** 
	 * Update all the views observing this model.
	 */
	protected void updateAllViews()
	{
		this.totCompares += this.compares;
		this.totMoves += this.moves;
		this.totOther += this.other;
		
		int totOps = this.compares + this.moves + this.other;
		for(IView v : this.views)
		{
			v.updateView(totOps);
		}
		this.compares = 0;
		this.moves = 0;
		this.other = 0;
	}

	public int getTotalCompares()
	{
		return this.totCompares;
	}

	public int getTotalMoves()
	{
		return this.totMoves;
	}

	public int getTotalOther()
	{
		return this.totOther;
	}
	
	/**
	 * Stop sorting.
	 */
	public void stop()
	{
		this.stopRequested = true;
	}

	/**
	 * Initialize
	 */
	public void init()
	{
		this.stopRequested = false;
		this.moves = this.totMoves = 0;
		this.compares = this.totCompares = 0;
		this.other = this.totOther = 0;
		this.activeMarker = this.lowMarker = this.hiMarker = -1;
		this.updateAllViews();
	}

	/**
	 * This method will be called to
	 * sort an array of integers.
	 * @throws IOException 
	 */
	abstract int[] sort(int a[]) throws IOException;

}