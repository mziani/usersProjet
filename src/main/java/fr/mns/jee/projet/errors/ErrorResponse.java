package fr.mns.jee.projet.errors;

public class ErrorResponse {

    private int responseCode;
    private String errorMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(int responseCode, String errorMessage) {
        this.responseCode = responseCode;
        this.errorMessage = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
