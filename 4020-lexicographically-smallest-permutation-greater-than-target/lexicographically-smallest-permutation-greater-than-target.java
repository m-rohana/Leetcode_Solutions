class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int[] remaining = count.clone();
        int position = -1;

        for (int i = 0; i < target.length(); i++) {
            int current = target.charAt(i) - 'a';

            for (int j = current + 1; j < 26; j++) {
                if (remaining[j] > 0) {
                    position = i;
                    break;
                }
            }

            if (remaining[current] == 0) {
                break;
            }

            remaining[current]--;
        }

        if (position == -1) {
            return "";
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < position; i++) {
            answer.append(target.charAt(i));
            count[target.charAt(i) - 'a']--;
        }

        int current = target.charAt(position) - 'a';

        for (int j = current + 1; j < 26; j++) {
            if (count[j] > 0) {
                answer.append((char) ('a' + j));
                count[j]--;
                break;
            }
        }

        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                answer.append((char) ('a' + i));
                count[i]--;
            }
        }

        return answer.toString();
    }
}