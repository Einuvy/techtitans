
const {createApp} = Vue

const index = createApp({
    data(){
        return {
           login: true, 
           products: [],
           productsFilter: [],
           searchInput: "",
           select: "Category",
           categories: [],
           productCart: []
        }
    },
    created(){
        
        this.loadData()
        
        if (localStorage.getItem("cart")) {
            this.productCart = JSON.parse(localStorage.getItem('cart'))
        }
    },
    methods: {
        
        loadData() {
            axios.get("/api/products")
            .then(res => {
              this.products = res.data.slice(0, 3)
              this.productsFilter = this.products
              console.log(this.products);
            }).catch(error => console.error(error))
        },
        addCart(product) {
            let alreadyInCart = this.productCart.find((item) => item.id === product.id)
            if (alreadyInCart) {
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Product already in cart"
                })
            } else {
                this.productCart.push(product);
                this.saveCartToLocalStorage()
            }

        },
        removeFromCart(product) {
            const productIndex = this.productCart.findIndex(p => p.id === product.id);
            if (productIndex === -1) return
            this.productCart.splice(productIndex, 1)
            this.saveCartToLocalStorage()
        },
        saveCartToLocalStorage() {
            localStorage.setItem("cart", JSON.stringify(this.productCart))
        },
            
    },
    computed: {

    }
})


index.mount('#index')