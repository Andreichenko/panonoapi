package de.andreichenko.panonoapi.model;


public class Panorama {

    private long count;

    private long timeStamp;

    private long sum;

    private double avg;

    private long max;

    private long min;

    public Panorama() {
        count=0;
    }

    public Panorama(long count, long timeStamp, long sum, double avg, long max, long min) {
        this.count = count;
        this.timeStamp = timeStamp;
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Panorama panorama = (Panorama) o;
        return count == panorama.count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Panorama{" +
                "count=" + count +
                ", timeStamp=" + timeStamp +
                ", sum=" + sum +
                ", avg=" + avg +
                ", max=" + max +
                ", min=" + min +
                '}';
    }
}
