package api seed desafio cdc;

import api seed desafio cdc.autores.AutoresRequestBuilder;
import api seed desafio cdc.categorias.CategoriasRequestBuilder;
import api seed desafio cdc.compras.ComprasRequestBuilder;
import api seed desafio cdc.cupons.CuponsRequestBuilder;
import api seed desafio cdc.estados.EstadosRequestBuilder;
import api seed desafio cdc.livros.LivrosRequestBuilder;
import api seed desafio cdc.paises.PaisesRequestBuilder;
import com.microsoft.kiota.ApiClientBuilder;
import com.microsoft.kiota.BaseRequestBuilder;
import com.microsoft.kiota.RequestAdapter;
import com.microsoft.kiota.serialization.ParseNodeFactoryRegistry;
import com.microsoft.kiota.serialization.SerializationWriterFactoryRegistry;
import java.util.HashMap;
import java.util.Objects;
/**
 * The main entry point of the SDK, exposes the configuration and the fluent API.
 */
@jakarta.annotation.Generated("com.microsoft.kiota")
public class api-client-seed-desafio-cdc extends BaseRequestBuilder {
    /**
     * The autores property
     * @return a {@link AutoresRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public AutoresRequestBuilder autores() {
        return new AutoresRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The categorias property
     * @return a {@link CategoriasRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public CategoriasRequestBuilder categorias() {
        return new CategoriasRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The compras property
     * @return a {@link ComprasRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public ComprasRequestBuilder compras() {
        return new ComprasRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The cupons property
     * @return a {@link CuponsRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public CuponsRequestBuilder cupons() {
        return new CuponsRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The estados property
     * @return a {@link EstadosRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public EstadosRequestBuilder estados() {
        return new EstadosRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The livros property
     * @return a {@link LivrosRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public LivrosRequestBuilder livros() {
        return new LivrosRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * The paises property
     * @return a {@link PaisesRequestBuilder}
     */
    @jakarta.annotation.Nonnull
    public PaisesRequestBuilder paises() {
        return new PaisesRequestBuilder(pathParameters, requestAdapter);
    }
    /**
     * Instantiates a new {@link api-client-seed-desafio-cdc} and sets the default values.
     * @param requestAdapter The request adapter to use to execute the requests.
     */
    public api-client-seed-desafio-cdc(@jakarta.annotation.Nonnull final RequestAdapter requestAdapter) {
        super(requestAdapter, "{+baseurl}");
        this.pathParameters = new HashMap<>();
        if (requestAdapter.getBaseUrl() == null || requestAdapter.getBaseUrl().isEmpty()) {
            requestAdapter.setBaseUrl("http://localhost:8080");
        }
        pathParameters.put("baseurl", requestAdapter.getBaseUrl());
    }
}
