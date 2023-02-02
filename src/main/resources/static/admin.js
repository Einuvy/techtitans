
const {createApp} = Vue

const admin = createApp({
    data(){
        return {
        //    login: true, 
         clients: [],
         products: [], 
         InputSearch: "",
         clientFilter: [],
         productFilter: [],
         images: [],
         idProduct: "",
         idClient: ""

        }
    },
    created(){
        this.loadData()
        
    },
    methods: {
     
        loadData() {
            axios.get("/rest/customers")
            .then((res) => {
                this.clients = res.data._embedded.customers
                console.log(this.clients)
            })
            .catch((error) => console.log(error))
            axios.get("/rest/products")
            .then((res) => {
                this.products = res.data._embedded.products
                console.log(this.products)
            })
            .catch((error) => console.log(error))
        },
        disableClient() {
            Swal.fire({
                    title: '¿Disable Client?',
                    showDenyButton: true,
                    // showCancelButton: true,
                    confirmButtonText: 'Accept',
                    denyButtonText: `Cancel`,
                })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios.patch('/api/customers/current/disabled', `idClient=${this.idClient}`)
                                        .then(response => {
                                            Swal.fire('Client deactivated', '', 'success')
                                                .then(result => {
                                                    window.location.href=("./admin.html")
                                                })
                                        }).catch(error => {
                                            this.error = error.response.data
                                            Swal.fire('Failed deactived client', this.error, 'error')
                                            
                                        })
                                
                            
                    }
                })
        },
        disableProduct() {
            Swal.fire({
                    title: '¿Disable Product?',
                    showDenyButton: true,
                    // showCancelButton: true,
                    confirmButtonText: 'Accept',
                    denyButtonText: `Cancel`,
                })
                .then((result) => {
                    if (result.isConfirmed) {
                        axios.patch('/api/product/disabled', `idProduct=${this.idProduct}`)
                                        .then(response => {
                                            Swal.fire('Product deactivated', '', 'success')
                                                .then(result => {
                                                    window.location.href=("./admin.html")
                                                })
                                        }).catch(error => {
                                            this.error = error.response.data
                                            Swal.fire('Failed deactived product', this.error, 'error')
                                            
                                        })
                                
                            
                    }
                })
        },
            
    },
    computed: {
        searchClient() {
            const client = this.clients.filter(client =>  client.email.includes(this.InputSearch.toLowerCase().trim()))
            this.clientFilter = client
        },
        searchProducts() {
            const product = this.products.filter(product =>  product.name.toLowerCase().trim().includes(this.InputSearch.toLowerCase().trim()))
            this.productFilter = product
        }
    }
})


admin.mount('#admin')