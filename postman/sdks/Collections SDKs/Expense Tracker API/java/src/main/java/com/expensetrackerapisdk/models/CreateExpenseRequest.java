package com.expensetrackerapisdk.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.openapitools.jackson.nullable.JsonNullable;

@Data
@Builder
@With
@ToString
@EqualsAndHashCode
@Jacksonized
public class CreateExpenseRequest {

  @JsonProperty("amount")
  private JsonNullable<Double> amount;

  @JsonProperty("currency")
  private JsonNullable<String> currency;

  @JsonProperty("dateIncurred")
  private JsonNullable<String> dateIncurred;

  @JsonProperty("categoryId")
  private JsonNullable<String> categoryId;

  @JsonProperty("description")
  private JsonNullable<String> description;

  @JsonProperty("paymentMethod")
  private JsonNullable<String> paymentMethod;

  @JsonProperty("clientReference")
  private JsonNullable<Object> clientReference;

  // FSM-59: capture unknown JSON fields so they round-trip on re-serialize.
  // @Builder.Default keeps the empty-map default in the Lombok-generated builder; without it the
  // builder would leave the map null and the any-setter would NPE on the first unknown field.
  // Deserialization is wired via the builder's @JsonAnySetter (see the Builder below), NOT here:
  // Lombok @Jacksonized deserializes through the builder and does not copy a field-level
  // @JsonAnySetter across, so unknown fields would be silently dropped if it lived on this field.
  @Builder.Default
  private Map<String, Object> additionalProperties = new HashMap<>();

  // @JsonAnyGetter must sit on the getter (not the field) so Jackson inlines the unknown entries on
  // serialize. On the field it double-registers with the Lombok getter and leaks a literal
  // "additionalProperties" property into every request body and object parameter.
  // Declaring the getter here also stops Lombok @Data from generating its own.
  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  @JsonIgnore
  public Double getAmount() {
    return amount.orElse(null);
  }

  @JsonIgnore
  public String getCurrency() {
    return currency.orElse(null);
  }

  @JsonIgnore
  public String getDateIncurred() {
    return dateIncurred.orElse(null);
  }

  @JsonIgnore
  public String getCategoryId() {
    return categoryId.orElse(null);
  }

  @JsonIgnore
  public String getDescription() {
    return description.orElse(null);
  }

  @JsonIgnore
  public String getPaymentMethod() {
    return paymentMethod.orElse(null);
  }

  @JsonIgnore
  public Object getClientReference() {
    return clientReference.orElse(null);
  }

  // Overwrite lombok builder methods
  public static class CreateExpenseRequestBuilder {

    private JsonNullable<Double> amount = JsonNullable.undefined();

    @JsonProperty("amount")
    public CreateExpenseRequestBuilder amount(Double value) {
      this.amount = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> currency = JsonNullable.undefined();

    @JsonProperty("currency")
    public CreateExpenseRequestBuilder currency(String value) {
      this.currency = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> dateIncurred = JsonNullable.undefined();

    @JsonProperty("dateIncurred")
    public CreateExpenseRequestBuilder dateIncurred(String value) {
      this.dateIncurred = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> categoryId = JsonNullable.undefined();

    @JsonProperty("categoryId")
    public CreateExpenseRequestBuilder categoryId(String value) {
      this.categoryId = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> description = JsonNullable.undefined();

    @JsonProperty("description")
    public CreateExpenseRequestBuilder description(String value) {
      this.description = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<String> paymentMethod = JsonNullable.undefined();

    @JsonProperty("paymentMethod")
    public CreateExpenseRequestBuilder paymentMethod(String value) {
      this.paymentMethod = JsonNullable.of(value);
      return this;
    }

    private JsonNullable<Object> clientReference = JsonNullable.undefined();

    @JsonProperty("clientReference")
    public CreateExpenseRequestBuilder clientReference(Object value) {
      this.clientReference = JsonNullable.of(value);
      return this;
    }

    @JsonAnySetter
    public CreateExpenseRequestBuilder additionalProperties(String key, Object value) {
      if (this.additionalProperties$value == null) {
        this.additionalProperties$value = new HashMap<>();
      }
      this.additionalProperties$value.put(key, value);
      this.additionalProperties$set = true;
      return this;
    }
  }
}
