package ru.unn.agile.Queue.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueStringTest {

    private LabQueue<String> testStrQueue;

    @Before
    public void setUpQueueForTests() {
        testStrQueue = new LabQueue<>();
    }

    @Test
    public void canPushString() {
        final String value1 = "first";
        testStrQueue.push(value1);

        final String value2 = testStrQueue.getHead();

        assertEquals(value1, value2);
    }

    @Test (expected = Exception.class)
    public void cantFindElement() throws Exception {
        testStrQueue.push("first");

        testStrQueue.findElement("second");
    }

    @Test
    public void checkHeadFromEmptyQueue() {
        final String value1 = testStrQueue.getHead();

        assertEquals(null, value1);
    }
}
