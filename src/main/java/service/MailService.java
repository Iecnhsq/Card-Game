package service;

import java.util.ArrayList;

public class MailService {

    public static boolean isValid(final char[] input) {
        if (input == null) {
            return false;
        }
        int state = 0;
        char ch;
        int index = 0;
        int mark = 0;
        String local = null;
        ArrayList<String> domain = new ArrayList<>();
        while (index <= input.length && state != -1) {
            if (index == input.length) {
                ch = '\0';
            } else {
                ch = input[index];
                if (ch == '\0') {
                    return false;
                }
            }
            switch (state) {
                case 0: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    state = -1;
                    break;
                }
                case 1: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        break;
                    }
                    if (ch == '.') {
                        state = 2;
                        break;
                    }
                    if (ch == '@') {
                        local = new String(input, 0, index - mark);
                        mark = index + 1;
                        state = 3;
                        break;
                    }
                    state = -1;
                    break;
                }
                case 2: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')
                            || (ch >= '0' && ch <= '9') || ch == '_' || ch == '-'
                            || ch == '+') {
                        state = 1;
                        break;
                    }
                    state = -1;
                    break;
                }
                case 3: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    state = -1;
                    break;
                }
                case 4: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        break;
                    }
                    if (ch == '-') {
                        state = 5;
                        break;
                    }
                    if (ch == '.') {
                        domain.add(new String(input, mark, index - mark));
                        mark = index + 1;
                        state = 5;
                        break;
                    }
                    if (ch == '\0') {
                        domain.add(new String(input, mark, index - mark));
                        state = 6;
                        break;
                    }
                    state = -1;
                    break;
                }
                case 5: {
                    if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                            || (ch >= 'A' && ch <= 'Z')) {
                        state = 4;
                        break;
                    }
                    if (ch == '-') {
                        break;
                    }
                    state = -1;
                    break;
                }

                case 6: {
                    break;
                }
            }
            index++;
        }
        if (state != 6) {
            return false;
        }
        if (domain.size() < 2) {
            return false;
        }
        if (local.length() > 64) {
            return false;
        }
        if (input.length > 254) {
            return false;
        }
        index = input.length - 1;
        while (index > 0) {
            ch = input[index];
            if (ch == '.' && input.length - index > 2) {
                return true;
            }
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                return false;
            }
            index--;
        }
        return true;
    }
}
