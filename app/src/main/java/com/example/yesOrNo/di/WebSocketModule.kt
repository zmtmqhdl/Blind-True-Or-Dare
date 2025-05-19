package com.example.yesOrNo.di

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .pingInterval(30, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideWebSocketRepository(
//        okHttpClient: OkHttpClient
//    ): WebSocketRepository {
//        return WebSocketRepositoryImpl(okHttpClient)
//    }
//}
//
//// repository/WebSocketRepository.kt
//interface WebSocketRepository {
//    fun startWebSocket()
//    fun sendMessage(message: String)
//    fun closeWebSocket()
//    fun getWebSocketState(): Flow<WebSocketState>
//    fun getMessages(): Flow<String>
//}
//
//// repository/WebSocketRepositoryImpl.kt
//class WebSocketRepositoryImpl @Inject constructor(
//    private val okHttpClient: OkHttpClient
//) : WebSocketRepository {
//    private var webSocket: WebSocket? = null
//    private val _webSocketState = MutableStateFlow<WebSocketState>(WebSocketState.Closed)
//    private val _messages = MutableSharedFlow<String>()
//
//    private val webSocketListener = object : WebSocketListener() {
//        override fun onOpen(webSocket: WebSocket, response: Response) {
//            _webSocketState.value = WebSocketState.Connected
//        }
//
//        override fun onMessage(webSocket: WebSocket, text: String) {
//            CoroutineScope(Dispatchers.IO).launch {
//                _messages.emit(text)
//            }
//        }
//
//        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
//            _webSocketState.value = WebSocketState.Closing
//        }
//
//        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
//            _webSocketState.value = WebSocketState.Closed
//        }
//
//        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//            _webSocketState.value = WebSocketState.Error(t)
//            // Implement reconnection logic here if needed
//        }
//    }
//
//    override fun startWebSocket() {
//        if (webSocket != null) return
//
//        val request = Request.Builder()
//            .url("wss://your-websocket-server-url")
//            .build()
//
//        webSocket = okHttpClient.newWebSocket(request, webSocketListener)
//    }
//
//    override fun sendMessage(message: String) {
//        webSocket?.send(message)
//    }
//
//    override fun closeWebSocket() {
//        webSocket?.close(1000, "Normal closure")
//        webSocket = null
//    }
//
//    override fun getWebSocketState(): Flow<WebSocketState> = _webSocketState.asStateFlow()
//
//    override fun getMessages(): Flow<String> = _messages.asSharedFlow()
//}
//
//// model/WebSocketState.kt
//sealed class WebSocketState {
//    object Connected : WebSocketState()
//    object Connecting : WebSocketState()
//    object Closing : WebSocketState()
//    object Closed : WebSocketState()
//    data class Error(val throwable: Throwable) : WebSocketState()
//}
//
//// viewmodel/WebSocketViewModel.kt
//@HiltViewModel
//class WebSocketViewModel @Inject constructor(
//    private val webSocketRepository: WebSocketRepository
//) : ViewModel() {
//
//    private val _messages = MutableStateFlow<List<String>>(emptyList())
//    val messages: StateFlow<List<String>> = _messages.asStateFlow()
//
//    val webSocketState = webSocketRepository.getWebSocketState()
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(5000),
//            WebSocketState.Closed
//        )
//
//    init {
//        viewModelScope.launch {
//            webSocketRepository.getMessages().collect { message ->
//                _messages.update { currentMessages ->
//                    currentMessages + message
//                }
//            }
//        }
//    }
//
//    fun connectWebSocket() {
//        webSocketRepository.startWebSocket()
//    }
//
//    fun sendMessage(message: String) {
//        webSocketRepository.sendMessage(message)
//    }
//
//    fun disconnectWebSocket() {
//        webSocketRepository.closeWebSocket()
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        disconnectWebSocket()
//    }
//}
//
//// MainActivity.kt
//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    private val viewModel: WebSocketViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            WebSocketScreen(viewModel)
//        }
//    }
//}
//
//// ui/WebSocketScreen.kt
//@Composable
//fun WebSocketScreen(viewModel: WebSocketViewModel) {
//    val messages by viewModel.messages.collectAsState()
//    val webSocketState by viewModel.webSocketState.collectAsState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Connection status
//        Text(
//            text = "Status: ${webSocketState::class.simpleName}",
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        // Messages list
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//        ) {
//            items(messages) { message ->
//                Text(
//                    text = message,
//                    modifier = Modifier.padding(vertical = 4.dp)
//                )
//            }
//        }
//
//        // Connection controls
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Button(onClick = { viewModel.connectWebSocket() }) {
//                Text("Connect")
//            }
//            Button(onClick = { viewModel.disconnectWebSocket() }) {
//                Text("Disconnect")
//            }
//        }
//    }
//}