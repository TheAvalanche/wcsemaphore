package com.tele2.semaphore

class WcDetails {
    String id
    WcStatus status

    enum WcStatus {
        opened,
        closed
    }
}
