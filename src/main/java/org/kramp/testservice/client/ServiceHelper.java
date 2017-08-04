package org.kramp.testservice.client;

public class ServiceHelper {

    final private static int HTTP_OK = 200;
    public static final Integer DEFAULT_LIMIT = 5;


    //The responses are not so big so that a Partial Content can be answered
    //and the only successfully response that i can think of is OK.
    //Also i tested, but maybe we could improve this.
    public static boolean isResponseOk(int status) {
        return status == HTTP_OK;
    }
}
