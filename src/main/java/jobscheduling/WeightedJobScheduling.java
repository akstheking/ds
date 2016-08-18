package jobscheduling;

import java.util.Arrays;

public class WeightedJobScheduling {

	private static class Job implements Comparable<Job> {
		int start, finish, profit;

		public Job(int start, int finish, int profit) {
			this.start = start;
			this.finish = finish;
			this.profit = profit;
		}

		@Override
		public int compareTo(Job o) {
			return this.finish - o.finish;
		}

		@Override
		public String toString() {
			return "Job [start=" + start + ", finish=" + finish + ", profit=" + profit + "]";
		}

	}

	public static void main(String[] args) {
		Job arr[] = { new Job(3, 10, 20), new Job(1, 2, 50), new Job(6, 19, 100), new Job(2, 100, 200) };
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		findMaxProfitDynamic(arr);
	}

	public static void findMaxProfitDynamic(Job[] jobs) {
		int temp[] = new int[jobs.length];
		temp[0] = jobs[0].profit;

		for (int i = 0; i < jobs.length; i++) {

			int last = findLastNonConflictingJob(jobs, i);
			int profitWith = jobs[i].profit;
			if (last != -1) {
				profitWith = jobs[i].profit + temp[last];
			}
			int profitWithout = (i>0)?temp[i - 1]:0;
			temp[i] = Math.max(profitWith, profitWithout);
		}
		
		System.out.println(Arrays.toString(temp));
	}

	public static void findMaxProfit(Job[] jobs) {
		int totalProfit = findMaxProfitUtil(jobs, jobs.length - 1);
		System.out.println(totalProfit);
	}

	public static int findMaxProfitUtil(Job[] jobs, int i) {
		if (i == 0) {
			return jobs[0].profit;
		}
		int last = findLastNonConflictingJob(jobs, i);
		int profitWith = jobs[i].profit;
		if (last >= 0) {
			profitWith = jobs[i].profit + findMaxProfitUtil(jobs, last);
		}
		int profitWithout = findMaxProfitUtil(jobs, i - 1);
		// System.out.println(i + " : " + last + " : " + profitWith + " : " +
		// profitWithout);
		return Math.max(profitWith, profitWithout);
	}

	public static int findLastNonConflictingJob(Job[] jobs, int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (jobs[j].finish <= jobs[i].start) {
				return j;
			}
		}
		return -1;
	}
	
	public static int findLastNonConflictingJobBinarySearch(Job[] jobs, int i) {
		
		int l =0;
		int h = i-1;
		int mid = -1;
		
		while(l<=h) {
			mid = (l+h)/2;;
			if(jobs[mid].finish <= jobs[i].start && jobs[mid+1].finish > jobs[i].start) {
				return mid;
			}
			
			if(jobs[mid].finish <= jobs[i].start) {
				l = mid + 1;
			} else {
				h = mid - 1;
			}
			
		}
		
		return -1;
	}

}
