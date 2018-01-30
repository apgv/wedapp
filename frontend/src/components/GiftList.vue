<template>
    <div>
        <h4 class="title is-4">Ønskeliste</h4>

        <div class="box">
            <div class="columns">
                <div class="column has-text-centered">
                    <i class="far fa-square fa-lg"></i>
                </div>
                <div class="column is-2">
                    Sofa fra IKEA
                </div>
                <div class="column is-9">
                    Dette er en sofa vi ønsker oss. Se <a href="http://www.ikea.com/no/no/catalog/products/S09129758/"
                                                          target="_blank">STOCKSUND
                    2-seters sofa, Ljungen blå, svart/tre</a>
                </div>
            </div>
        </div>

        <div class="box">
            <div class="columns">
                <div class="column has-text-centered">
                    <i class="fas fa-check fa-lg"></i>
                </div>
                <div class="column is-2">
                    <span style="text-decoration: line-through">Kaffebord</span>
                </div>
                <div class="column is-9">
                    <span style="text-decoration: line-through">
                        Kaffebord fra butikken på hjørnet
                    </span>
                </div>
            </div>
        </div>

        <div class="box">
            <div class="columns">
                <div class="column has-text-centered">
                    <i class="fas fa-circle fa-lg"></i>
                </div>
                <div class="column is-2">
                    Tilbords
                </div>
                <div class="column is-9">
                    Vi har lagt ut ønskeliste hos Tilbords her <a href="https://www.tilbords.no/bryllup"
                                                                  target="_blank">Tilbords
                    ønskeliste</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'gift-list'
}
</script>
