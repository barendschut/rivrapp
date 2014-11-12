package nl.ing.hackathon.dialog.domain;

public class DialogueRequest {

    private final String query;
    private final String url;

    public DialogueRequest(final String query, final String url) {
        this.query = query;
        this.url = url;
    }

    public String getQuery() {
        return query;
    }

    public String getUrl() {
        return url;
    }

}