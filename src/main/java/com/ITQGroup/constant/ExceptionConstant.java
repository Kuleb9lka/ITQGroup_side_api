package com.ITQGroup.constant;

public final class ExceptionConstant {

    public static final String NEGATIVE_FILE_QUANTITY = "File quantity can't be equals zero or less: ";

    public static final String FAILED_TO_READ_FILE = "Failed to read document file quantity: ";

    public static final String EMPTY_QUANTITY_FILE = "Document quantity file is empty";

    public static final String FAILED_TO_PARE_DOCUMENT_QUANTITY = "Failed to parse document quantity to integer: ";

    public static final String FAILED_TO_DECODE_RESPONSE = "Failed to decode response. HTTP status: ";

    public static final String EMPTY_BODY_RESPONSE = "Empty body response: ";

    public static final String UNEXPECTED_STATUS = "Unexpected status: %s, message: %s";


    private ExceptionConstant() {
    }
}
