package sudgoyal.gitusers;

public class IssueContent {
    String Issue;
    String Detail;
    String HtmlUrl;

    public IssueContent(){

    }

    public IssueContent(String issue, String detail, String htmlUrl) {
        Issue = issue;
        Detail = detail;
        HtmlUrl = htmlUrl;
    }

    public String getHtmlUrl() {
        return HtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        HtmlUrl = htmlUrl;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }
}
