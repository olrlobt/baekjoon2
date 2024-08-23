class Solution
{
    public int solution(String s)
    {
       
        
        int size = s.length();
        long[] bits = new long[size + 1];

        for (int idx = 0; idx < size; idx++) {
            bits[idx + 1] = bits[idx] ^ (1L << (s.charAt(idx) - 'a'));
        }

        int answer = 1;

        for (int left = 0; left + answer <= size; left++) {
            for (int right = left + answer; right <= size; right++) {

                long temp = bits[right] ^ bits[left];

                int numOfBits = countBits(temp);
                if (numOfBits != 1 && numOfBits != 0) {
                    continue;
                }

                int tempLeft = left;
                int tempRight = right - 1;

                int count = 0;
                while (tempLeft < tempRight && s.charAt(tempLeft++) == s.charAt(tempRight--)) {
                    count++;
                }

                if (count == (right - left) / 2) {
                    answer = (right - left) ;
                }
            }
        }
        return answer;
    }
    
    public static int countBits(long n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}