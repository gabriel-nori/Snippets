try{
                String urlString = "https://cloud.iexapis.com/stable/stock/" + syb.getSymbol()  + "/peers?token=" + token;
                URL url = new URL(urlString);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);

                int status = con.getResponseCode();
                if(status == 200){
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    try {
                        String read = content.toString();
                        JSONArray jsonArray = new JSONArray(read);
                        pgd.upsertPeerGroupsMarketdata(id_stock, jsonArray.toString());

                    } catch (JSONException e) {
                        logger.error("Parse Error  ", e);
                    }
                }
                con.disconnect();
            }
            catch (Exception e){
                logger.error("deu ruim", e);
            }
