import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1197_최소스패닝트리 {

	private static int[] parents;
	private static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 1<=V<=10,000
		int E = Integer.parseInt(st.nextToken()); // 1<=E<=100,000
		int[][] edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edges[i][0] =  Integer.parseInt(st.nextToken()) -1 ;
			edges[i][1] =  Integer.parseInt(st.nextToken()) -1;
			edges[i][2] =  Integer.parseInt(st.nextToken()); // 음수일수도있다. -1,000,000 < 
		}
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		parents = new int[V];
		rank = new int[V];
		
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
		
		int cnt = 1;
		int result = 0;
		for (int i = 0; i < E; i++) {
			int a = findSet(edges[i][0]); // 부모 찾아주기
			int b = findSet(edges[i][1]); // 부모 찾아주기
			
			if(a==b) { // 부모가 같으면
				continue;
			}
			union(a,b);
			result += edges[i][2];
			cnt++;
			if(cnt == V) {
				break;
			}
		}
		System.out.println(result);

	}

	private static void union(int a, int b) {
		if(rank[a] > rank[b]) {
			parents[b] = a;
		}else {
			parents[a] = b;
			if( rank[a] == rank[b]) {
				rank[b]++;
			}
		}
		
	}

	private static int findSet(int x) {
		if(parents[x] == x) {
			return x;
		}else {
			return findSet(parents[x]);
		}
	}

}
