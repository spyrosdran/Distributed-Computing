FROM python:3
ADD server.py
ADD server_protocol.py
RUN pip install flask
RUN pip install flask_cors
RUN pip install mysqlclient
CMD ["python", "./server.py"]