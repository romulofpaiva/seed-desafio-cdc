package api seed desafio cdc.cupons;

import com.microsoft.kiota.serialization.AdditionalDataHolder;
import com.microsoft.kiota.serialization.Parsable;
import com.microsoft.kiota.serialization.ParseNode;
import com.microsoft.kiota.serialization.SerializationWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@jakarta.annotation.Generated("com.microsoft.kiota")
public class CuponsPostRequestBody implements AdditionalDataHolder, Parsable {
    /**
     * Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     */
    private Map<String, Object> additionalData;
    /**
     * The codigo property
     */
    private String codigo;
    /**
     * The desconto property
     */
    private Float desconto;
    /**
     * The validade property
     */
    private LocalDate validade;
    /**
     * Instantiates a new {@link CuponsPostRequestBody} and sets the default values.
     */
    public CuponsPostRequestBody() {
        this.setAdditionalData(new HashMap<>());
    }
    /**
     * Creates a new instance of the appropriate class based on discriminator value
     * @param parseNode The parse node to use to read the discriminator value and create the object
     * @return a {@link CuponsPostRequestBody}
     */
    @jakarta.annotation.Nonnull
    public static CuponsPostRequestBody createFromDiscriminatorValue(@jakarta.annotation.Nonnull final ParseNode parseNode) {
        Objects.requireNonNull(parseNode);
        return new CuponsPostRequestBody();
    }
    /**
     * Gets the AdditionalData property value. Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     * @return a {@link Map<String, Object>}
     */
    @jakarta.annotation.Nonnull
    public Map<String, Object> getAdditionalData() {
        return this.additionalData;
    }
    /**
     * Gets the codigo property value. The codigo property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getCodigo() {
        return this.codigo;
    }
    /**
     * Gets the desconto property value. The desconto property
     * @return a {@link Float}
     */
    @jakarta.annotation.Nullable
    public Float getDesconto() {
        return this.desconto;
    }
    /**
     * The deserialization information for the current model
     * @return a {@link Map<String, java.util.function.Consumer<ParseNode>>}
     */
    @jakarta.annotation.Nonnull
    public Map<String, java.util.function.Consumer<ParseNode>> getFieldDeserializers() {
        final HashMap<String, java.util.function.Consumer<ParseNode>> deserializerMap = new HashMap<String, java.util.function.Consumer<ParseNode>>(3);
        deserializerMap.put("codigo", (n) -> { this.setCodigo(n.getStringValue()); });
        deserializerMap.put("desconto", (n) -> { this.setDesconto(n.getFloatValue()); });
        deserializerMap.put("validade", (n) -> { this.setValidade(n.getLocalDateValue()); });
        return deserializerMap;
    }
    /**
     * Gets the validade property value. The validade property
     * @return a {@link LocalDate}
     */
    @jakarta.annotation.Nullable
    public LocalDate getValidade() {
        return this.validade;
    }
    /**
     * Serializes information the current object
     * @param writer Serialization writer to use to serialize this model
     */
    public void serialize(@jakarta.annotation.Nonnull final SerializationWriter writer) {
        Objects.requireNonNull(writer);
        writer.writeStringValue("codigo", this.getCodigo());
        writer.writeFloatValue("desconto", this.getDesconto());
        writer.writeLocalDateValue("validade", this.getValidade());
        writer.writeAdditionalData(this.getAdditionalData());
    }
    /**
     * Sets the AdditionalData property value. Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     * @param value Value to set for the AdditionalData property.
     */
    public void setAdditionalData(@jakarta.annotation.Nullable final Map<String, Object> value) {
        this.additionalData = value;
    }
    /**
     * Sets the codigo property value. The codigo property
     * @param value Value to set for the codigo property.
     */
    public void setCodigo(@jakarta.annotation.Nullable final String value) {
        this.codigo = value;
    }
    /**
     * Sets the desconto property value. The desconto property
     * @param value Value to set for the desconto property.
     */
    public void setDesconto(@jakarta.annotation.Nullable final Float value) {
        this.desconto = value;
    }
    /**
     * Sets the validade property value. The validade property
     * @param value Value to set for the validade property.
     */
    public void setValidade(@jakarta.annotation.Nullable final LocalDate value) {
        this.validade = value;
    }
}
