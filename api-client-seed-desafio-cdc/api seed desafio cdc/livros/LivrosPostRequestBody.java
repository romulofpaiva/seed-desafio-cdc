package api seed desafio cdc.livros;

import com.microsoft.kiota.serialization.AdditionalDataHolder;
import com.microsoft.kiota.serialization.Parsable;
import com.microsoft.kiota.serialization.ParseNode;
import com.microsoft.kiota.serialization.SerializationWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@jakarta.annotation.Generated("com.microsoft.kiota")
public class LivrosPostRequestBody implements AdditionalDataHolder, Parsable {
    /**
     * Stores additional data not described in the OpenAPI description found when deserializing. Can be used for serialization as well.
     */
    private Map<String, Object> additionalData;
    /**
     * The dataPublicacao property
     */
    private LocalDate dataPublicacao;
    /**
     * The idAutor property
     */
    private Integer idAutor;
    /**
     * The idCategoria property
     */
    private Integer idCategoria;
    /**
     * The isbn property
     */
    private String isbn;
    /**
     * The paginas property
     */
    private Integer paginas;
    /**
     * The preco property
     */
    private Float preco;
    /**
     * The resumo property
     */
    private String resumo;
    /**
     * The sumario property
     */
    private String sumario;
    /**
     * The titulo property
     */
    private String titulo;
    /**
     * Instantiates a new {@link LivrosPostRequestBody} and sets the default values.
     */
    public LivrosPostRequestBody() {
        this.setAdditionalData(new HashMap<>());
    }
    /**
     * Creates a new instance of the appropriate class based on discriminator value
     * @param parseNode The parse node to use to read the discriminator value and create the object
     * @return a {@link LivrosPostRequestBody}
     */
    @jakarta.annotation.Nonnull
    public static LivrosPostRequestBody createFromDiscriminatorValue(@jakarta.annotation.Nonnull final ParseNode parseNode) {
        Objects.requireNonNull(parseNode);
        return new LivrosPostRequestBody();
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
     * Gets the dataPublicacao property value. The dataPublicacao property
     * @return a {@link LocalDate}
     */
    @jakarta.annotation.Nullable
    public LocalDate getDataPublicacao() {
        return this.dataPublicacao;
    }
    /**
     * The deserialization information for the current model
     * @return a {@link Map<String, java.util.function.Consumer<ParseNode>>}
     */
    @jakarta.annotation.Nonnull
    public Map<String, java.util.function.Consumer<ParseNode>> getFieldDeserializers() {
        final HashMap<String, java.util.function.Consumer<ParseNode>> deserializerMap = new HashMap<String, java.util.function.Consumer<ParseNode>>(9);
        deserializerMap.put("dataPublicacao", (n) -> { this.setDataPublicacao(n.getLocalDateValue()); });
        deserializerMap.put("idAutor", (n) -> { this.setIdAutor(n.getIntegerValue()); });
        deserializerMap.put("idCategoria", (n) -> { this.setIdCategoria(n.getIntegerValue()); });
        deserializerMap.put("isbn", (n) -> { this.setIsbn(n.getStringValue()); });
        deserializerMap.put("paginas", (n) -> { this.setPaginas(n.getIntegerValue()); });
        deserializerMap.put("preco", (n) -> { this.setPreco(n.getFloatValue()); });
        deserializerMap.put("resumo", (n) -> { this.setResumo(n.getStringValue()); });
        deserializerMap.put("sumario", (n) -> { this.setSumario(n.getStringValue()); });
        deserializerMap.put("titulo", (n) -> { this.setTitulo(n.getStringValue()); });
        return deserializerMap;
    }
    /**
     * Gets the idAutor property value. The idAutor property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getIdAutor() {
        return this.idAutor;
    }
    /**
     * Gets the idCategoria property value. The idCategoria property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getIdCategoria() {
        return this.idCategoria;
    }
    /**
     * Gets the isbn property value. The isbn property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getIsbn() {
        return this.isbn;
    }
    /**
     * Gets the paginas property value. The paginas property
     * @return a {@link Integer}
     */
    @jakarta.annotation.Nullable
    public Integer getPaginas() {
        return this.paginas;
    }
    /**
     * Gets the preco property value. The preco property
     * @return a {@link Float}
     */
    @jakarta.annotation.Nullable
    public Float getPreco() {
        return this.preco;
    }
    /**
     * Gets the resumo property value. The resumo property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getResumo() {
        return this.resumo;
    }
    /**
     * Gets the sumario property value. The sumario property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getSumario() {
        return this.sumario;
    }
    /**
     * Gets the titulo property value. The titulo property
     * @return a {@link String}
     */
    @jakarta.annotation.Nullable
    public String getTitulo() {
        return this.titulo;
    }
    /**
     * Serializes information the current object
     * @param writer Serialization writer to use to serialize this model
     */
    public void serialize(@jakarta.annotation.Nonnull final SerializationWriter writer) {
        Objects.requireNonNull(writer);
        writer.writeLocalDateValue("dataPublicacao", this.getDataPublicacao());
        writer.writeIntegerValue("idAutor", this.getIdAutor());
        writer.writeIntegerValue("idCategoria", this.getIdCategoria());
        writer.writeStringValue("isbn", this.getIsbn());
        writer.writeIntegerValue("paginas", this.getPaginas());
        writer.writeFloatValue("preco", this.getPreco());
        writer.writeStringValue("resumo", this.getResumo());
        writer.writeStringValue("sumario", this.getSumario());
        writer.writeStringValue("titulo", this.getTitulo());
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
     * Sets the dataPublicacao property value. The dataPublicacao property
     * @param value Value to set for the dataPublicacao property.
     */
    public void setDataPublicacao(@jakarta.annotation.Nullable final LocalDate value) {
        this.dataPublicacao = value;
    }
    /**
     * Sets the idAutor property value. The idAutor property
     * @param value Value to set for the idAutor property.
     */
    public void setIdAutor(@jakarta.annotation.Nullable final Integer value) {
        this.idAutor = value;
    }
    /**
     * Sets the idCategoria property value. The idCategoria property
     * @param value Value to set for the idCategoria property.
     */
    public void setIdCategoria(@jakarta.annotation.Nullable final Integer value) {
        this.idCategoria = value;
    }
    /**
     * Sets the isbn property value. The isbn property
     * @param value Value to set for the isbn property.
     */
    public void setIsbn(@jakarta.annotation.Nullable final String value) {
        this.isbn = value;
    }
    /**
     * Sets the paginas property value. The paginas property
     * @param value Value to set for the paginas property.
     */
    public void setPaginas(@jakarta.annotation.Nullable final Integer value) {
        this.paginas = value;
    }
    /**
     * Sets the preco property value. The preco property
     * @param value Value to set for the preco property.
     */
    public void setPreco(@jakarta.annotation.Nullable final Float value) {
        this.preco = value;
    }
    /**
     * Sets the resumo property value. The resumo property
     * @param value Value to set for the resumo property.
     */
    public void setResumo(@jakarta.annotation.Nullable final String value) {
        this.resumo = value;
    }
    /**
     * Sets the sumario property value. The sumario property
     * @param value Value to set for the sumario property.
     */
    public void setSumario(@jakarta.annotation.Nullable final String value) {
        this.sumario = value;
    }
    /**
     * Sets the titulo property value. The titulo property
     * @param value Value to set for the titulo property.
     */
    public void setTitulo(@jakarta.annotation.Nullable final String value) {
        this.titulo = value;
    }
}
