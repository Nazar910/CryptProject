'use strict';

import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

const App = React.createClass({
    getInitialState() {
        return {
            message: "",
            key: 1,
            crypt: "encrypt"
        }  
    },

    onKeyChange(event) {
        this.setState({ key: event.target.value });
    },

    onMessageChange(event) {
        this.setState({ message: event.target.value });
    },

    encryptRequest() {
        axios.post('/api/crypt/caesar/encrypt', {message:this.state.message, key:this.state.key})
          .then((response)=>{
            return response.data;
        })
          .then((data)=>{ this.setState({message: data}); });
    },

    decryptRequest() {
        axios.post('/api/crypt/caesar/decrypt', {message:this.state.message, key:this.state.key})
          .then((response)=>{
            return response.data;
        })
          .then((data)=>{ this.setState({message: data}); });
    },

    onEncryptButton() {
      this.setState({ crypt: "encrypt" })
    },

    onDecryptButton() {
      this.setState({ crypt: "decrypt" })
    },

    render() {
        return (
            <div>
                <div className="container">
                  <div className="header clearfix">
                    <nav>
                      <ul className="nav nav-pills pull-right">
                        <li role="presentation"><a href="#" onClick={this.onEncryptButton}>Encrypt</a></li>
                        <li role="presentation"><a href="#" onClick={this.onDecryptButton}>Decrypt</a></li>
                      </ul>
                    </nav>
                    <h3 className="text-muted">Project name</h3>
                  </div>

                  <div className="jumbotron">
                    <textarea 
                    className="form-control" value={this.state.message}
                    rows="5" id="comment" onChange={this.onMessageChange}>
                      
                    </textarea>
                    <input 
                    type="number" className="form-control" 
                    id="key" onChange={this.onKeyChange}
                    value={this.state.key}
                    />
                    <p id="crypt">
                      { this.state.crypt === "encrypt" ?
                          <button 
                          className="btn btn-lg btn-success" onClick={this.encryptRequest}>
                            Encrypt
                          </button>
                          :
                          <button 
                          className="btn btn-lg btn-success" onClick={this.decryptRequest}>
                            Decrypt
                          </button>
                      }
                    </p>
                  </div>

                  <footer className="footer">
                    <p>&copy; 2017 Nazarii Pyvovar TR-41</p>
                  </footer>

            </div>
        </div>
        )
    }
});

export default App;