
const {createApp} = Vue

const admin = createApp({
    data(){
        return {
        //    login: true, 
         clients: {},
         InputSearch: "",
         clientFilter: []

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

        }
            
    },
    computed: {
        searchClient() {
            const client = this.clients.filter(client =>  client.email.includes(this.InputSearch.toLowerCase().trim()))
            this.clientFilter = client
        }
    }
})


admin.mount('#admin')