package ua.com.alevel;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class StringTest {

    public void test() {
//        String s1 = new String("Java");
        String s = "Java";
        s = s + " Best";
        System.out.println("s = " + s);
        String language = s.concat(" ");
        language = language
                .concat("Language")
                .concat("!!!")
                .concat("\n");
        System.out.println("language = " + language);

        language = language.toUpperCase();
        System.out.println("language = " + language);
        language = language.toLowerCase();
        System.out.println("language = " + language);

        char[] chars = language.toCharArray();
        for (char aChar : chars) {
            System.out.println("aChar = " + aChar);
        }

        byte[] bytes = language.getBytes();
        for (byte aByte : bytes) {
            System.out.println("aByte = " + aByte);
        }

        String empty = " ";
        String blank = " \t";
        System.out.println("empty = " + empty.isEmpty());
        System.out.println("blank = " + blank.isBlank());

        blank = null;
        if (blank != null) {
            System.out.println("not blank = " + !blank.isBlank());
        }
        System.out.println("not blank = " + StringUtils.isNotBlank(blank));

        String text = "Мама мыла раму";
        String[] s1 = text.split(" ");
        for (String s2 : s1) {
            System.out.println("s2 = " + s2);
        }

        char c = text.charAt(0);
        System.out.println("c = " + c);

        String ss1 = "Text";
        String ss2 = "TexT";
        System.out.println(ss1.equals(ss2));
        System.out.println(ss1.equalsIgnoreCase(ss2));

        System.out.println(ss1.startsWith("Tes"));

        ss1 = "Test Text T";
        System.out.println("ss1 = " + ss1);
        ss1 = ss1.substring(2);
        System.out.println("ss1 = " + ss1);
        ss1 = ss1.substring(2, 6);
        System.out.println("ss1 = " + ss1);
        ss1 = ss1.replace(" ", "!!!");
        System.out.println("ss1 = " + ss1);




//        int a = 10;
//        a = a + 5;
//        a += 5;
    }

    private void generateSql(Map<String, String> map) {
        String sql = "";
        String table = map.get("table");
        sql = "Select from " + table;
        String order = map.get("order");
        if (StringUtils.isNotBlank(order)) {
            sql = sql + "order by " + order;
        }
        String limit = map.get("limit");
        if (StringUtils.isNotBlank(limit)) {
            sql = sql + "limit (" + limit + ")";
        }

        StringBuilder sb = new StringBuilder("");
        sb.append(map.get("table"));
        sb.append("order by ");
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));
        sb.append(map.get("order"));

        String s = sb.toString();
    }
}
