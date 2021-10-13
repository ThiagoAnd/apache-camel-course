package br.com.thiago.camelmicroserviceb.model;

import java.math.BigDecimal;

public class CurrencyExchange {
    Long id;
    String from;
    String to;
    BigDecimal conversionMultiple;

    public CurrencyExchange() {
    }

    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CurrencyExchange TO STRING:{");
        sb.append("id=").append(id);
        sb.append(", from='").append(from).append('\'');
        sb.append(", to='").append(to).append('\'');
        sb.append(", conversionMultiple=").append(conversionMultiple);
        sb.append('}');
        return sb.toString();
    }
}