Classe DatagramSocket
La classe Java DatagramSocket usa il protocollo UDP per trasmettere dati attraverso i socket. 
Permette ad un client di connettersi a una determinata porta di un host per leggere e scrivere dati impacchettati attraverso la classe DatagramPacket.
I principali metodi della classe DatagramSocket sono i seguenti:
- METODI COSTRUTTORI: void DatagramSocket() throws SocketExeption
		      void DatagramSocket(int porta) throws SocketExeption 

- METODI PER INVIARE/RICEVERE: void send(DatagramPacket pacchettoDati) throws IOException
			       void receive(DatagramPacket pacchettoDati) throws IOException //il metodo receive è un metodo bloccante(blocca il chiamante fino a quando un pacchetto è ricevuto)

- METODO PER CHIUDERE LA CONNESSIONE: void close()   


Classe DatagramPacket
La classe Java DatagramPacket permette di rappresentare i pacchetti UDP da trasmettere e ricevere sui socket di tipo datagram.
Il costruttore della classe è: public DatagramPacket(byte buffer[], int lunghezza, InetAddress, indirizzo, int porta)
Il server per ricevere un messaggio, crea un oggetto di tipo DatagramPacket: public DatagramPacket(byte buffer[], int lunghezza)
I principali metodi sono i seguenti:
- METODI PER LA GESTIONE DELL'INDIRIZZO IP: void setAddress(InetAddress indirizzo) //setta il valore dell'indirizzo IP
					    InetAddress getAddress() //restituisce l'indirizzo IP dell'host da cui il pacchetto è stato ricevuto

- METODI PER LA GESTIONE DELLA PORTA: void setPort(int porta)
				      int getPort()

- METODI PER LA GESTIONE DEI DATI: void setData(byte[] buffer) //inserisce i dati nel pacchetto 
				   byte[] getData() //restituisce i dati del pacchetto



Classe InetAddress 
InetAddress rappresenta un indirizzo IP e fornisce metodi per accedere a informazioni sul sistema di rete. 
È definita nel pacchetto java.net e contiene metodi statici.
La classe InetAddress è astratta e non può essere istanziata direttamente. Invece, è possibile ottenere istanze di InetAddress tramite i suoi metodi statici come ad esempio:
- public static InetAddress getByName(String host): restituisce un'istanza di InetAddress per il nome host specificato. Se il nome host non è valido, viene sollevata un'eccezione UnknownHostException.
- public static InetAddress getLocalHost(): restituisce l'istanza di InetAddress corrispondente alla macchina locale, se tale macchina non è registrata, oppure è protetta da un firewall, l'indirizzo è quello di loopback: 127.0.0.1.
Una volta ottenuta un'istanza di InetAddress, è possibile accedere alle sue proprietà come ad esempio il nome host o l'indirizzo IP associato. 
Alcuni dei metodi forniti dalla classe InetAddress includono:
- public String getHostName(): restituisce il nome host associato all'istanza di InetAddress.
- public String getHostAddress(): restituisce l'indirizzo IP associato all'istanza di InetAddress.
- public byte[] getAddress()
