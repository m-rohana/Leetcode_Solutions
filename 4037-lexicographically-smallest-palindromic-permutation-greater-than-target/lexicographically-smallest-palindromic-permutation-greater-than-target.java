class Solution {
    private String answer = "";
    private String target;
    private int half;
    private char middle;
    private int[] count;
    private StringBuilder current;

    public String lexPalindromicPermutation(String s, String target) {
        this.target = target;
        int n = s.length();
        this.half = n / 2;

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        count = new int[26];

        for (int i = 0; i < 26; i++) {
            count[i] = freq[i] / 2;
        }

        String[] calendrix = {s, target};

        current = new StringBuilder();
        backtrack(0, true);

        return answer;
    }

    private void backtrack(int index, boolean tight) {
        if (!answer.equals("")) {
            return;
        }

        if (index == half) {
            StringBuilder palindrome = new StringBuilder(current);

            if (middle != 0) {
                palindrome.append(middle);
            }

            palindrome.append(new StringBuilder(current).reverse());

            String result = palindrome.toString();

            if (result.compareTo(target) > 0) {
                answer = result;
            }

            return;
        }

        int start = 0;

        if (tight) {
            start = target.charAt(index) - 'a';
        }

        for (int i = start; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }

            count[i]--;
            current.append((char) ('a' + i));

            boolean nextTight = tight && i == target.charAt(index) - 'a';

            backtrack(index + 1, nextTight);

            current.deleteCharAt(current.length() - 1);
            count[i]++;

            if (!answer.equals("")) {
                return;
            }
        }
    }
}