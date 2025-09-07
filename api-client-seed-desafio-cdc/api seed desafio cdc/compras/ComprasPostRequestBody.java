package api seed desafio cdc.compras;

import com.microsoft.kiota.serialization.AdditionalDataHolder;
import com.microsoft.kiota.serialization.Parsable;
import com.microsoft.kiota.serialization.ParseNode;
import com.microsoft.kiota.serialization.SerializationWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@jakarta.annotation.Generated("com.microsoft.kiota")
public class ComprasPostRequestBody implements AdditionalDataHolder, Parsable {
    /**
     * Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     */
    private Map<String, Object> additionalData;
    /**
     * The emailCliente property
     */
    private String emailCliente;
    /**
     * The idLivro property
     */
    private Integer idLivro;
    /**
     * The quantidade property
     */
    private Integer quantidade;
    /**
     * Instantiates a new {@link ComprasPostRequestBody} and sets the default values.
     */
    public ComprasPostRequestBody() {
        this.setAdditionalData(new HashMap<>());
    }
    /**
     * Creates a new instance of the appropriate class based on discriminator value
     * @param parseNode The parse node to use to read the discriminator value and create the object
     * @return a {@link ComprasPostRequestBody}
     */
    @jakarta.annotation.Nonnull
    public static ComprasPostRequestBody createFromDiscriminatorValue(@jakarta.annotation.Nonnull final ParseNode parseNode) {
        Objects.requireNonNull(parseNode);
        return new ComprasPostRequestBody();
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
     * Gets the emailCliente property value. The emailCliente property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getEmailCliente() {
        return this.emailCliente;
    }
    /**
     * The deserialization information for the current model
     * @return a {@link Map<String, java.util.function.Consumer<ParseNode>>}
     */
    @jakarta.annotation.Nonnull
    public Map<String, java.util.function.Consumer<ParseNode>> getFieldDeserializers() {
        final HashMap<String, java.util.function.Consumer<ParseNode>> deserializerMap = new HashMap<String, java.util.function.Consumer<ParseNode>>(3);
        deserializerMap.put("emailCliente", (n) -> { this.setEmailCliente(n.getStringValue()); });
        deserializerMap.put("idLivro", (n) -> { this.setIdLivro(n.getIntegerValue()); });
        deserializerMap.put("quantidade", (n) -> { this.setQuantidade(n.getIntegerValue()); });
        return deserializerMap;
    }
    /**
     * Gets the idLivro property value. The idLivro property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getIdLivro() {
        return this.idLivro;
    }
    /**
     * Gets the quantidade property value. The quantidade property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getQuantidade() {
        return this.quantidade;
    }
    /**
     * Serializes information the current object
     * @param writer Serialization writer to use to serialize this model
     */
    public void serialize(@jakarta.annotation.Nonnull final SerializationWriter writer) {
        Objects.requireNonNull(writer);
        writer.writeStringValue("emailCliente", this.getEmailCliente());
        writer.writeIntegerValue("idLivro", this.getIdLivro());
        writer.writeIntegerValue("quantidade", this.getQuantidade());
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
     * Sets the emailCliente property value. The emailCliente property
     * @param value Value to set for the emailCliente property.
     */
    public void setEmailCliente(@jakarta.annotation.Nullable final String value) {
        this.emailCliente = value;
    }
    /**
     * Sets the idLivro property value. The idLivro property
     * @param value Value to set for the idLivro property.
     */
    public void setIdLivro(@jakarta.annotation.Nullable final Integer value) {
        this.idLivro = value;
    }
    /**
     * Sets the quantidade property value. The quantidade property
     * @param value Value to set for the quantidade property.
     */
    public void setQuantidade(@jakarta.annotation.Nullable final Integer value) {
        this.quantidade = value;
    }
}
