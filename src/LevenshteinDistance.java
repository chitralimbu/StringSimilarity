import java.util.Arrays;

public class LevenshteinDistance {
	/*This is to find the edit distance between two strings*/
	/*Thanks to Baeldung: https://www.baeldung.com/java-levenshtein-distance
	 * and Back To Back SWE: https://www.youtube.com/watch?v=MiqoA-yF-0M 
	 * for helping me understand this problem */
	
	/*
	 * KEY
	 * [REPLACE][INSERT]
	 * [DELETE ][INDEX ] <- Imagine you are at index
	 * 
	 * Matrix a =
	 * 	  ["][b][e][n][y][a][m]
	 * ["][0][1][1][1][1][1][1] 
	 * [e][1][1][1][2][3][4][5]
	 * [p][2][2][2][2][3][4][5]
	 * [r][3][3][3][3][3][4][5]
	 * [h][4][4][4][4][4][4][5]
	 * [e][5][5][4][5][5][5][5]
	 * [m][6][6][5][5][6][6][5]
	 * 
	 * benyam.length() = i
	 * eprhem.length() = j
	 * 
	 * the edit distance is at: b[i + 1, j + 1]
	 * 
	 * */
	public static int calculate(String x, String y) {
		int[][] dp = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			for (int j = 0; j <= y.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				}
				else if (j == 0) {
					dp[i][j] = i;
				}
				else {
					dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
							dp[i - 1][j] + 1, 
							dp[i][j - 1] + 1);
				}
			}
		}
		
		return dp[x.length()][y.length()];
	}

	public static int costOfSubstitution(char a, char b) {
		return a == b ? 0 : 1;
	}

	public static int min(int... numbers) {
		return Arrays.stream(numbers)
				.min().orElse(Integer.MAX_VALUE);
	}
	public static void main(String[] args) {
		String s = "benyam";
		String t = "ephrem";
		
		System.out.println(calculate(s,t));
	}
}
