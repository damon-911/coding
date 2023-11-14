package programmers.string.매칭점수;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    static class PageInfo {
        String name; // 본 페이지 URL
        int outerCnt; // 본 페이지가 가지고 있는 외부 URL 갯수
        int basicScore; // 본 페이지가 가지고 있는 기본 Score
        List<String> linkedOutPage; // 본 페이지가 가지고 있는 외부 URL
        double score; // 기본 Score + 외부 url 계산 score
    }

    public static int solution(String word, String[] pages) {
        int answer = 0;

        word = word.toLowerCase();
        Matcher matcher = null;

        PageInfo[] pageInfos = new PageInfo[pages.length];
        List<List<String>> datas = new ArrayList<>();

        // 각 페이지에 관한 정보 정리
        for (int i = 0; i < pages.length; i++) {
            int score = 0;
            pages[i] = pages[i].toLowerCase();
            pageInfos[i] = new PageInfo();
            datas.add(new ArrayList<>());

            // 본 페이지 url를 구하는 코드
            matcher = Pattern.compile("(<meta property=\"og:url\" content=\"https://(\\S*)\")").matcher(pages[i]);
            while (matcher.find()) {
                String name = matcher.group(2).trim();
                pageInfos[i].name = name;
            }

            // 본 페이지 basicScore를 구하는 코드
            matcher = Pattern.compile("(?<=[^a-zA-Z])(" + word + ")[^a-zA-Z]").matcher(pages[i]);
            while (matcher.find()) {
                score += 1;
            }
            pageInfos[i].basicScore = score;

            //// 본 페이지 외부 url를 구하는 코드
            matcher = Pattern.compile("<a href=\"(\\S*)//(\\S*)\"").matcher(pages[i]);
            while (matcher.find()) {
                String url = matcher.group(2).trim();
                datas.get(i).add(url);
            }
            pageInfos[i].linkedOutPage = datas.get(i);
            pageInfos[i].outerCnt = datas.get(i).size();
        }

        // 외부 링크를 계산하여 총점을 구하는 코드
        for (int i = 0; i < pageInfos.length; i++) {
            for (String url : pageInfos[i].linkedOutPage) {
                for (int k = 0; k < pageInfos.length; k++) {
                    if (i == k) {
                        continue;
                    }
                    if (url.equals(pageInfos[k].name)) {
                        pageInfos[k].score += (double) pageInfos[i].basicScore / pageInfos[i].linkedOutPage.size();
                    }
                }
            }
        }

        // 최대 스코어의 index를 구하는 코드
        double max = 0;
        for (int i = 0; i < pageInfos.length; i++) {
            if (max < (pageInfos[i].basicScore + pageInfos[i].score)) {
                max = (pageInfos[i].basicScore + pageInfos[i].score);
                answer = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String word = "blind";
        String[] pages = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        };

        System.out.println(solution(word, pages));
    }
}