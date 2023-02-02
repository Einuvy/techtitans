
const {createApp} = Vue

const settings = createApp({
    data(){
        return {
        //    login: true, 
        emailVerification: "",
        currentPassword: "",
        newPassword: "",
        }
    },
    created(){
        
        
    },
    methods: {
        changePassword() {
            Swal.fire({
                title: '¿Do you want to change your password??',
                showDenyButton: true,
                confirmButtonText: 'Accept',
                denyButtonText: `Cancel`,
            }).then((result) => {
                
                if (result.isConfirmed) {
                    axios.patch(
                                "/api/clients/customers/password",
                                `newPassword=${this.newPassword}&password=${this.currentPassword}&email=${this.emailVerification}`
                            )
                            .then(response => {
                                Swal.fire('Change password Success', '', 'success')
                                    .then(result => {
                                        window.location.href=("./index.html")
                                    })
                            }).catch((error) => Swal.fire({
                                icon: "error",
                                title: "Oops...",
                                text: `${error.response.data}`,
                            }));
                }else {
                    Swal.fire('Change password canceled')
                                .then(result => {
                                    window.location.reload()
                                })
                }
        
                
            })
        }
            
    },
    computed: {

    }
})


settings.mount('#settings')