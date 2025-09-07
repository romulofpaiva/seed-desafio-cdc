package api seed desafio cdc.estados;

import com.microsoft.kiota.serialization.AdditionalDataHolder;
import com.microsoft.kiota.serialization.Parsable;
import com.microsoft.kiota.serialization.ParseNode;
import com.microsoft.kiota.serialization.SerializationWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@jakarta.annotation.Generated("com.microsoft.kiota")
public class EstadosPostResponse implements AdditionalDataHolder, Parsable {
    /**
     * Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     */
    private Map<String, Object> additionalData;
    /**
     * The id property
     */
    private Integer id;
    /**
     * The idPais property
     */
    private Integer idPais;
    /**
     * The nome property
     */
    private String nome;
    /**
     * Instantiates a new {@link EstadosPostResponse} and sets the default values.
     */
    public EstadosPostResponse() {
        this.setAdditionalData(new HashMap<>());
    }
    /**
     * Creates a new instance of the appropriate class based on discriminator value
     * @param parseNode The parse node to use to read the discriminator value and create the object
     * @return a {@link EstadosPostResponse}
     */
    @jakarta.annotation.Nonnull
    public static EstadosPostResponse createFromDiscriminatorValue(@jakarta.annotation.Nonnull final ParseNode parseNode) {
        Objects.requireNonNull(parseNode);
        return new EstadosPostResponse();
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
     * The deserialization information for the current model
     * @return a {@link Map<String, java.util.function.Consumer<ParseNode>>}
     */
    @jakarta.annotation.Nonnull
    public Map<String, java.util.function.Consumer<ParseNode>> getFieldDeserializers() {
        final HashMap<String, java.util.function.Consumer<ParseNode>> deserializerMap = new HashMap<String, java.util.function.Consumer<ParseNode>>(3);
        deserializerMap.put("id", (n) -> { this.setId(n.getIntegerValue()); });
        deserializerMap.put("idPais", (n) -> { this.setIdPais(n.getIntegerValue()); });
        deserializerMap.put("nome", (n) -> { this.setNome(n.getStringValue()); });
        return deserializerMap;
    }
    /**
     * Gets the id property value. The id property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getId() {
        return this.id;
    }
    /**
     * Gets the idPais property value. The idPais property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getIdPais() {
        return this.idPais;
    }
    /**
     * Gets the nome property value. The nome property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getNome() {
        return this.nome;
    }
    /**
     * Serializes information the current object
     * @param writer Serialization writer to use to serialize this model
     */
    public void serialize(@jakarta.annotation.Nonnull final SerializationWriter writer) {
        Objects.requireNonNull(writer);
        writer.writeIntegerValue("id", this.getId());
        writer.writeIntegerValue("idPais", this.getIdPais());
        writer.writeStringValue("nome", this.getNome());
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
     * Sets the id property value. The id property
     * @param value Value to set for the id property.
     */
    public void setId(@jakarta.annotation.Nullable final Integer value) {
        this.id = value;
    }
    /**
     * Sets the idPais property value. The idPais property
     * @param value Value to set for the idPais property.
     */
    public void setIdPais(@jakarta.annotation.Nullable final Integer value) {
        this.idPais = value;
    }
    /**
     * Sets the nome property value. The nome property
     * @param value Value to set for the nome property.
     */
    public void setNome(@jakarta.annotation.Nullable final String value) {
        this.nome = value;
    }
}
